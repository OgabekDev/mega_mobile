package uz.nlg.mega.screens.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.model.Product
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.ProductSearchType
import uz.nlg.mega.views.AddingProductItem
import uz.nlg.mega.views.CategoryItem
import uz.nlg.mega.views.CategoryTopItem
import uz.nlg.mega.views.MainButton
import uz.nlg.mega.views.SearchAndFilterTopSection

@Composable
fun ProductsScreen(
    navigator: DestinationsNavigator? = null
) {
    var searchText by remember {
        mutableStateOf("")
    }

    var productType by remember {
        mutableStateOf(ProductSearchType.None)
    }

    var isSearching by remember {
        mutableStateOf(false)
    }

    var inCategory by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color_F6)
    ) {
        Column {
            SearchAndFilterTopSection(
                isBack = true,
                title = stringResource(id = R.string.str_take_order),
                onBackClick = {
                    navigator!!.navigateUp()
                },
                onEditTextBackClick = {
                    searchText = ""
                }
            ) {
                searchText = it
                isSearching = searchText != ""
            }

            if (!isSearching && !inCategory) Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MainButton(
                    modifier = Modifier
                        .weight(1f),
                    text = stringResource(id = R.string.str_category),
                    textColor = if (productType == ProductSearchType.ByCategory) Color.White else Color_66,
                    textSize = 13.sp,
                    isTextBold = false,
                    backgroundColor = if (productType == ProductSearchType.ByCategory) MainColor else Color.White,
                    strokeColor = if (productType == ProductSearchType.ByCategory) MainColor else Color_E8
                ) {
                    productType = if (productType == ProductSearchType.ByCategory)
                        ProductSearchType.None
                    else
                        ProductSearchType.ByCategory
                }

                Spacer(modifier = Modifier.width(16.dp))

                MainButton(
                    modifier = Modifier
                        .weight(1f),
                    text = stringResource(id = R.string.str_more_sold),
                    textColor = if (productType == ProductSearchType.ByMoreSold) Color.White else Color_66,
                    textSize = 13.sp,
                    isTextBold = false,
                    backgroundColor = if (productType == ProductSearchType.ByMoreSold) MainColor else Color.White,
                    strokeColor = if (productType == ProductSearchType.ByMoreSold) MainColor else Color_E8
                ) {
                    productType = if (productType == ProductSearchType.ByMoreSold)
                        ProductSearchType.None
                    else
                        ProductSearchType.ByMoreSold
                }
            }

            if (!isSearching && inCategory) LazyRow(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                for (i in 0..0) {
                    item {
                        CategoryTopItem(
                            title = "Kategoriyalar",
                            isLast = false
                        )
                    }
                }
                item {
                    CategoryTopItem(
                        title = "Kategoriyalar",
                        isLast = true
                    )
                }
            }

            if (isSearching || productType != ProductSearchType.ByCategory) {
                LazyColumn(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    for (i in 1..30) {
                        item {
                            AddingProductItem(
                                search = searchText,
                                product = Product(
                                    id = 1,
                                    name = "Sayding L-Brus-15x240",
                                    quantity = 55,
                                    firstQuantityType = "dona",
                                    secondQuantityType = "pachka",
                                    coefficient = "",
                                    price = 10_000
                                )
                            ) {}
                        }
                    }
                }
            }

            if (!isSearching && productType == ProductSearchType.ByCategory) {
                LazyColumn(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    for (i in 0..15) {
                        item {
                            CategoryItem(
                                id = 1,
                                title = "Kategoriyalar",
                                quantity = 22
                            ) {
                                inCategory = true
                            }
                        }
                    }
                }
            }

        }
    }
}
