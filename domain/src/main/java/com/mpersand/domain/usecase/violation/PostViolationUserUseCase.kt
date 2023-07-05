package com.mpersand.domain.usecase.violation

import com.mpersand.domain.model.violation.request.ViolationRequestModel
import com.mpersand.domain.repository.ViolationRepository
import javax.inject.Inject

class PostViolationUserUseCase @Inject constructor(
    private val violationRepository: ViolationRepository
) {
    suspend operator fun invoke(violationRequestModel: ViolationRequestModel) = kotlin.runCatching { violationRepository.postViolationUser(violationRequestModel) }
}