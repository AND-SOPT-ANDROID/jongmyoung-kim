package org.sopt.and.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.GetHobbyUseCase
import org.sopt.and.presentation.search.sideeffect.SearchSideEffect
import org.sopt.and.presentation.search.uistate.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getHobbyUseCase: GetHobbyUseCase
) : ViewModel() {

    val uiState: StateFlow<SearchUiState>
        field = MutableStateFlow(SearchUiState())

    val sideEffect: SharedFlow<SearchSideEffect>
        field = MutableSharedFlow<SearchSideEffect>()

    // dummy data for week2, later will be replaced by API
    val dummyPopularSeriesPosters: List<Pair<String, String>> = listOf(
        Pair(
            "지옥에서 온 판사",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202409/1726468460609282978.webp"
        ),
        Pair(
            "런닝맨",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_S01_V0000330171-Horizontal_LogoY.webp"
        ),
        Pair(
            "이토록 친밀한 배신자",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202410/1728548589061678015.webp"
        ),
        Pair(
            "나 혼자 산다",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202311/1699601581063498561.webp"
        ),
        Pair(
            "미운 우리 새끼",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_S01_V2000009613-Horizontal_LogoY.webp"
        ),
        Pair(
            "심야괴담회",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202406/1718946016997717704.webp"
        ),
        Pair(
            "전지적 참견 시점",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_M_1003594100000100000-Horizontal_LogoY.webp"
        ),
        Pair(
            "스캔들",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202406/1718155800481547422.webp"
        ),
        Pair(
            "그것이 알고싶다",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_S01_V0000010101-Horizontal_LogoY.webp"
        ),
        Pair(
            "꼬리에 꼬리를 무는 그날 이야기",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_S01_P451575582-Horizontal_LogoY-RE.webp"
        )
    )
    val dummyPopularMoviePosters: List<Pair<String, String>> = listOf(
        Pair(
            "꼬리에 꼬리를 무는 그날 이야기",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_S01_P451575582-Horizontal_LogoY-RE.webp"
        ),
        Pair(
            "그것이 알고싶다",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_S01_V0000010101-Horizontal_LogoY.webp"
        ),
        Pair(
            "스캔들",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202406/1718155800481547422.webp"
        ),
        Pair(
            "전지적 참견 시점",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_M_1003594100000100000-Horizontal_LogoY.webp"
        ),
        Pair(
            "심야괴담회",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202406/1718946016997717704.webp"
        ),
        Pair(
            "미운 우리 새끼",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_S01_V2000009613-Horizontal_LogoY.webp"
        ),
        Pair(
            "나 혼자 산다",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202311/1699601581063498561.webp"
        ),
        Pair(
            "이토록 친밀한 배신자",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202410/1728548589061678015.webp"
        ),
        Pair(
            "런닝맨",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/BMS/TVProgram/2022/PRG_S01_V0000330171-Horizontal_LogoY.webp"
        ),
        Pair(
            "지옥에서 온 판사",
            "https://image.wavve.com/v1/thumbnails/936_528_20_80/meta/image/202409/1726468460609282978.webp"
        ),
    )

    init {
        getSearchPosters()
    }

    fun getSearchPosters() {
        uiState.value = uiState.value.copy(
            popularSeriesPosters = dummyPopularSeriesPosters,
            popularMoviePosters = dummyPopularMoviePosters
        )
    }

    fun updateSearchInput(searchInput: String) {
        uiState.value = uiState.value.copy(
            searchInput = searchInput
        )
    }

    fun onTabClicked(index: Int) {
        uiState.value = uiState.value.copy(
            selectedTabIndex = index
        )
    }

    fun onHobbySearched(no: String) = viewModelScope.launch {
        getHobbyUseCase(no).onSuccess {
            sideEffect.emit(SearchSideEffect.ShowSnackbar(it.hobby))
        }.onFailure {
            sideEffect.emit(SearchSideEffect.Toast(it.message.orEmpty()))
        }
    }
}
