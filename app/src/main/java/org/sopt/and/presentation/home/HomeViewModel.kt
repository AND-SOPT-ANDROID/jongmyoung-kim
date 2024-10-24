package org.sopt.and.presentation.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    val uiState: StateFlow<HomeUiState>
        field = MutableStateFlow(HomeUiState())

    // dummy data for week2, later will be replaced by API
    val dummyMainBanners: List<Pair<String, String>> = listOf(
        Pair(
            "https://image.wavve.com/banner/pooq/2023/20230623_banner_134915.jpg",
            "https://image.wavve.com/operation/image/banner/202410/1729148886479475026.png"
        ),
        Pair(
            "https://image.wavve.com/banner/pooq/2024/20240619_banner_110308.jpg",
            "https://image.wavve.com/banner/pooq/2024/imgbuild_20240619_110418912.png"
        ),
        Pair(
            "https://image.wavve.com/banner/pooq/2024/20240702_banner_173926.jpg",
            "https://image.wavve.com/operation/image/banner/202410/1728033775764541591.png"
        ),
        Pair(
            "https://image.wavve.com/banner/pooq/2024/20240718_banner_103245.jpg",
            "https://image.wavve.com/banner/pooq/2024/imgbuild_20240718_104322567.png"
        ),
        Pair(
            "https://image.wavve.com/operation/image/banner/202410/1728982187593768680.jpg",
            "https://image.wavve.com/operation/image/banner/202410/1729140337731996324.png"
        )
    )

    val dummyRecommendedPosters: List<Pair<String, List<String>>> = listOf(
        Pair(
            "믿고 보는 웨이브 에디터 추천작",
            listOf(
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202409/1727073001312600950.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202409/1727679685902220639.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202410/1728609264868811411.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202410/1728548543503727711.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202409/1726468505828994516.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202409/1727255007631341114.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202410/1729053449522305685.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/BMS/TVSeason/2023/C9901_C99000000062-Vertical_LogoY_WOriginal.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202409/1727416060204368451.webp"
            )
        ),
        Pair(
            "실시간 인기 콘텐츠",
            listOf(
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202409/1726468505828994516.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202311/1698908219745378929.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202401/1704938429614651417.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202406/1718945888434131419.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202311/1699601487098289299.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202410/1728548543503727711.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202401/1705627894585408800.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/BMS/TVSeason/2022/S01_V0000010101-Vertical_LogoY.webp",
                "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202409/1725261620306609215.webp"
            )
        )
    )

    val dummyRankedPoster: Pair<String, List<String>> = Pair(
        "오늘의 영화 TOP 20",
        listOf(
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202407/1720170421133162998.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/movieImg/MovieGroup/2022/GMV_CD01_WR0000011251-Vertical_LogoY.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/movieImg/MovieGroup/2023/GMV_C901_SG0000117325-Vertical_LogoY.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/movieImg/MovieGroup/2023/GMV_CI01_LE0000011184-Vertical_LogoY.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/movieImg/MovieGroup/2022/GMV_CD01_WR0000011244-Vertical_LogoY.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202410/1728631705498572177.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202410/1729058271607741038.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/meta/image/202410/1729058061796753462.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/movieImg/MovieGroup/2022/GMV_CD01_WR0000011250-Vertical_LogoY.webp",
            "https://image.wavve.com/v1/thumbnails/312_468_20_80/movieImg/MovieGroup/2022/GMV_CD01_WR0000011247-Vertical_LogoY.webp"
        )
    )

    init {
        getMainBannersAndPosters()
    }

    private fun getMainBannersAndPosters() {
        uiState.value = uiState.value.copy(
            banners = dummyMainBanners,
            posters = dummyRecommendedPosters,
            rankedPosters = dummyRankedPoster
        )
    }
}

@Immutable
data class HomeUiState(
    val isLoading: Boolean = false, // 추후 로딩 뷰 추가 시 사용
    val banners: List<Pair<String, String>> = emptyList(),
    val posters: List<Pair<String, List<String>>> = emptyList(),
    val rankedPosters: Pair<String, List<String>> = Pair("", emptyList())
)