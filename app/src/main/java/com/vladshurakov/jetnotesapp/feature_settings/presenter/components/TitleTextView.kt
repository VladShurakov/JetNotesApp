package com.vladshurakov.jetnotesapp.feature_settings.presenter.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vladshurakov.jetnotesapp.theme.MainTheme

@Composable
fun TitleTextView(
    @StringRes name: Int,
    @DrawableRes icon: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(start = 18.dp, top = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = name),
            tint = MainTheme.colors.invertColor
        )

        Text(
            text = stringResource(id = name),
            style = MainTheme.typography.title,
            color = MainTheme.colors.primaryTextColor,
            modifier = Modifier
                .padding(start = 12.dp)
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}