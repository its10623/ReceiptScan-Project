package com.smoothsm.cameraapp.presentation.ui.screen

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.smoothsm.cameraapp.presentation.ui.theme.Ink
import com.smoothsm.cameraapp.presentation.ui.theme.Primary
import com.smoothsm.cameraapp.presentation.ui.theme.Surface
import com.smoothsm.cameraapp.presentation.ui.theme.TextSub
import java.io.File
import java.io.FileOutputStream

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    onImageCaptured: (File) -> Unit = {},
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val executor = ContextCompat.getMainExecutor(context)

    var flashState by remember { mutableStateOf(false) }

    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        label = "scale",
    )

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED,
        )
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
        ) { granted ->
            hasCameraPermission = granted
        }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) launcher.launch(Manifest.permission.CAMERA)
    }

    if (hasCameraPermission) {
        val cameraController = remember { LifecycleCameraController(context) }
        Box(modifier = modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                },
            )
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .background(
                            Brush.verticalGradient(
                                colors =
                                    listOf(
                                        Color.Black.copy(alpha = 0.6f),
                                        Color.Transparent,
                                    ),
                            ),
                        ),
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxHeight(0.4f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {},
                            modifier =
                                Modifier
                                    .size(46.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = "뒤로 가기",
                                tint = Surface,
                                modifier =
                                    Modifier
                                        .size(30.dp),
                            )
                        }

                        Text(
                            text = "영수증 스캔",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Surface,
                            modifier =
                                Modifier
                                    .padding(start = 26.dp),
                        )

                        Box(
                            modifier =
                                Modifier
                                    .clip(shape = RoundedCornerShape(16.dp))
                                    .background(if (flashState) Primary else TextSub)
                                    .clickable {
                                        flashState = !flashState
                                        cameraController.enableTorch(flashState)
                                    }
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = if (flashState) "플래시 켬" else "플래시 끔",
                                style = MaterialTheme.typography.labelSmall,
                                color = Surface,
                            )
                        }
                    }
                }
                Row(
                    modifier =
                        Modifier
                            .padding(top = 120.dp)
                            .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "영수증이 화면에 가득 차게 찍어주세요",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Surface,
                    )
                }
            }
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors =
                                    listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.6f),
                                    ),
                            ),
                        ),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 80.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier.size(48.dp))

                    Box(
                        modifier =
                            Modifier
                                .scale(scale)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            isPressed = true
                                            tryAwaitRelease()
                                            isPressed = false
                                        },
                                        onTap = {
                                            val file =
                                                File(
                                                    context.filesDir,
                                                    "receipt_${System.currentTimeMillis()}.jpg",
                                                )
                                            val outputOptions =
                                                ImageCapture.OutputFileOptions.Builder(file).build()
                                            cameraController.takePicture(
                                                outputOptions,
                                                executor,
                                                object : ImageCapture.OnImageSavedCallback {
                                                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                                        rotateImageIfNeeded(file)
                                                        onImageCaptured(file)
                                                    }

                                                    override fun onError(e: ImageCaptureException) {
                                                        // 에러 처리
                                                    }
                                                },
                                            )
                                        },
                                    )
                                }
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(Surface),
                        contentAlignment = Alignment.Center,
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .size(66.dp)
                                    .clip(CircleShape)
                                    .background(Ink),
                            contentAlignment = Alignment.Center,
                        ) {
                            Box(
                                modifier =
                                    Modifier
                                        .size(58.dp)
                                        .clip(CircleShape)
                                        .background(Primary),
                            )
                        }
                    }

                    Box(modifier = Modifier.size(48.dp))
                }
            }
            Canvas(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.65f)
                        .align(Alignment.Center),
            ) {
                val width = size.width
                val height = size.height
                val radius = 16.dp.toPx()
                val length = 40.dp.toPx()
                val inset = 20.dp.toPx()

                val path =
                    Path().apply {
                        moveTo(inset, inset + length)
                        lineTo(inset, inset + radius)
                        quadraticTo(inset, inset, inset + radius, inset)
                        lineTo(inset + length, inset)

                        moveTo(width - inset - length, inset)
                        lineTo(width - inset - radius, inset)
                        quadraticTo(width - inset, inset, width - inset, inset + radius)
                        lineTo(width - inset, inset + length)

                        moveTo(inset, height - inset - length)
                        lineTo(inset, height - inset - radius)
                        quadraticTo(inset, height - inset, inset + radius, height - inset)
                        lineTo(inset + length, height - inset)

                        moveTo(width - inset - length, height - inset)
                        lineTo(width - inset - radius, height - inset)
                        quadraticTo(
                            width - inset,
                            height - inset,
                            width - inset,
                            height - inset - radius,
                        )
                        lineTo(width - inset, height - inset - length)
                    }

                drawPath(
                    path,
                    color = Primary.copy(alpha = 0.08f),
                    style = Stroke(width = 8.dp.toPx()),
                )
                drawPath(
                    path,
                    color = Primary.copy(alpha = 0.15f),
                    style = Stroke(width = 6.dp.toPx()),
                )
                drawPath(
                    path,
                    color = Primary.copy(alpha = 0.3f),
                    style = Stroke(width = 4.dp.toPx()),
                )
                drawPath(path, color = Primary, style = Stroke(width = 2.dp.toPx()))
            }
        }
    } else {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("카메라 권한이 필요합니다")
        }
    }
}

private fun rotateImageIfNeeded(file: File) {
    val exif = ExifInterface(file.absolutePath)
    val orientation =
        exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL,
        )
    val degrees =
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90f
            ExifInterface.ORIENTATION_ROTATE_180 -> 180f
            ExifInterface.ORIENTATION_ROTATE_270 -> 270f
            else -> return
        }
    val bitmap = BitmapFactory.decodeFile(file.absolutePath) ?: return
    val matrix = Matrix().apply { postRotate(degrees) }
    val rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    FileOutputStream(file).use { rotated.compress(Bitmap.CompressFormat.JPEG, 95, it) }
    exif.setAttribute(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL.toString())
    exif.saveAttributes()
    bitmap.recycle()
    rotated.recycle()
}
