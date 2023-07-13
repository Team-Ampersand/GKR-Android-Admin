package com.mpersand.presentation.view.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DrawerState
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mpersand.domain.model.user.response.UserResponseModel
import com.mpersand.presentation.R
import com.mpersand.presentation.view.modifier.gkrClickable

@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    userInfo: UserResponseModel,
    logoutAction: () -> Unit,
    navigateToViolation: () -> Unit,
    navigateToRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val contents = listOf("메인페이지", "학생제재", "요청내역", "로그아웃")
    val resourceIds = listOf(R.drawable.ic_folder, R.drawable.ic_profile, R.drawable.ic_handshake, R.drawable.ic_exit_door)

    var selectedItem by remember { mutableStateOf(0) }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(modifier = modifier.systemBarsPadding()) {
                Image(
                    modifier = modifier.padding(top = 17.dp, start = 17.dp),
                    painter = if (userInfo.profileUrl != null) rememberAsyncImagePainter(userInfo.profileUrl)
                    else painterResource(id = R.drawable.ic_logo),
                    contentDescription = "profile"
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    modifier = modifier.padding(horizontal = 20.dp),
                    text = userInfo.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xB2000000),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = modifier.padding(horizontal = 20.dp),
                    text = "선생님",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFCBCBCB),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.height(14.dp))
                Divider(
                    color = Color(0x33000000),
                    thickness = 1.dp
                )
                repeat(contents.size) {
                    DrawerItem(
                        selected = it == selectedItem,
                        resourceId = resourceIds[it],
                        content = contents[it],
                        onItemClick = {
                            selectedItem = it
                            when (it) {
                                1 -> navigateToViolation()
                                2 -> navigateToRequest()
                                3 -> logoutAction()
                            }
                        }
                    )
                }
            }
        },
    ) {
        content()
    }
}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    resourceId: Int,
    content: String,
    selected: Boolean,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 5.dp
            )
            .background(
                color = if (selected) Color(0xFF865DFF) else Color.Transparent,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .gkrClickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = "icon",
            colorFilter = ColorFilter.tint(
                if (selected) Color.White else Color(0xFFd9d9d9)
            )
        )
        Spacer(modifier = modifier.width(14.dp))
        Text(
            text = content,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color.White else Color(0xFF999999),
            textAlign = TextAlign.Center
        )
    }
}