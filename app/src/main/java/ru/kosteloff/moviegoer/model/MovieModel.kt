package ru.kosteloff.moviegoer.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class MovieModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val result: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

@Parcelize
@Entity (tableName = "movies_table")
data class Result(

    @PrimaryKey (autoGenerate = true)
    @SerializedName ("id")
    val id : Int,

    @ColumnInfo
    @SerializedName("title")
    val title: String,

    @ColumnInfo
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo
    @SerializedName("overview")
    val overView: String,

    @ColumnInfo
    @SerializedName("release_date")
    val releaseDate: String,

    @ColumnInfo
    @SerializedName("popularity")
    val popularity: Double
) : Parcelable