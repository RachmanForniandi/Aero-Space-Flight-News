package rachman.forniandi.core.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class AuthorContents(
    val name: String?
): Parcelable
