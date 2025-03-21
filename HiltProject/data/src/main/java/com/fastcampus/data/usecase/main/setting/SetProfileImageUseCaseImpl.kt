package com.fastcampus.data.usecase.main.setting

import com.fastcampus.data.di.FC_HOST
import com.fastcampus.domain.usecase.file.GetImageUseCase
import com.fastcampus.domain.usecase.file.UploadImageUseCase
import com.fastcampus.domain.usecase.main.setting.GetMyUserUseCase
import com.fastcampus.domain.usecase.main.setting.SetMyUserUseCase
import com.fastcampus.domain.usecase.main.setting.SetProfileImageUseCase
import javax.inject.Inject

class SetProfileImageUseCaseImpl @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val setMyUserUseCase: SetMyUserUseCase,
    private val getMyUserUseCase: GetMyUserUseCase
): SetProfileImageUseCase {

    override suspend fun invoke(contentUri: String): Result<Unit> = kotlin.runCatching{
        // 0. 내 정보 가져오기
        val user = getMyUserUseCase().getOrThrow()
        // 1. 이미지 정보 가져오기
        val image = getImageUseCase(contentUri = contentUri) ?: throw NullPointerException("이미지를 찾을 수 없습니다")
        // 2. 이미지 업로드 하기
        val imagePath = uploadImageUseCase(image).getOrThrow()
        // 3. 내 정보 업데이트 하기
        setMyUserUseCase(
            profileImageUrl = FC_HOST + imagePath
        ).getOrThrow()
    }
}
