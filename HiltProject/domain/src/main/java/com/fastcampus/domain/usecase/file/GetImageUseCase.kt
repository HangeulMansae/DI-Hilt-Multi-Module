package com.fastcampus.domain.usecase.file

import com.fastcampus.domain.usecase.model.Image

interface GetImageUseCase {
    operator fun invoke(contentUri: String): Image?
}