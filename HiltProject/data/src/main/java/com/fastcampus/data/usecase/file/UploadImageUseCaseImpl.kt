package com.fastcampus.data.usecase.file

import com.fastcampus.data.retrofit.FileService
import com.fastcampus.data.retrofit.UriRequestBody
import com.fastcampus.domain.usecase.file.GetInputStreamUseCase
import com.fastcampus.domain.usecase.file.UploadImageUseCase
import com.fastcampus.domain.usecase.model.Image
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadImageUseCaseImpl @Inject constructor(
    private val fileService: FileService,
    private val getInputStreamUseCase:GetInputStreamUseCase
): UploadImageUseCase {
    override suspend fun invoke(
        image: Image
    ): Result<String> = kotlin.runCatching{
        val fileNamePart = MultipartBody.Part.createFormData(
            "fileName",
            image.name
        )
        val requestBody = UriRequestBody(
            contentUri = image.uri,
            getInputStreamUseCase = getInputStreamUseCase,
            contentType = image.mimeType.toMediaType(),
            contentLength = image.size
        )
        val filePart = MultipartBody.Part.createFormData(
            "file",
            image.name
        )
        fileService.uploadFile(
            fileName = fileNamePart,
            file = filePart
        ).data.filePath
    }
}