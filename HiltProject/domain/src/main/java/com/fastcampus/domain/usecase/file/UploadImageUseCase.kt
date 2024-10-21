package com.fastcampus.domain.usecase.file

import com.fastcampus.domain.usecase.model.Image

interface UploadImageUseCase {
    suspend operator fun invoke(
        image: Image
    ): Result<String>
}