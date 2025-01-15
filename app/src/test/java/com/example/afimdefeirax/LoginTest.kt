package com.example.afimdefeirax


import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.afimdefeirax.SharedPreferences.ILoginShared
import com.example.afimdefeirax.Utils.FirebaseAuth.FirebaseAuthServiceImpl
import com.example.afimdefeirax.ViewModel.LoginViewModel
import com.example.afimdefeirax.ViewModel.State.LoginUiState
import com.google.android.gms.tasks.Task
import com.google.common.truth.Truth
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LoginTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)


    @Mock
    private lateinit var mockAuth: FirebaseAuth

    @Mock
    private lateinit var mockTask: Task<AuthResult>

    @Mock
    private lateinit var mockAuthResult: AuthResult

    @Mock
    private lateinit var mockFirebaseUser: FirebaseUser

    @Mock
    private lateinit var mockLoginShared: ILoginShared

    private lateinit var viewModel: LoginViewModel

    private lateinit var mockAuthService: FirebaseAuthServiceImpl


    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)
        Dispatchers.resetMain()
        Dispatchers.setMain(testDispatcher)

        mockAuthService = mock()
        mockLoginShared = mock()
        viewModel = LoginViewModel(
            authservice = mockAuthService,
            loginShared = mockLoginShared,
            skipInit = true
        )

    }

    @Test
    fun Given_receive_user_and_password_Then_update_state() = testScope.runTest {

        val userName = "user@example.com"
        val password = "password123"

    }

    @Test
    fun `When process login with empty username Then return login failed`() = testScope.runTest {

        val userName = ""
        val password = ""

        // Given
        `when`(mockAuthService.signInWithEmailAndPassword(userName, password))
            .thenThrow(IllegalArgumentException("Invalid credentials"))

        // When
        viewModel.login()

        // Then
        val state = viewModel.state.value
        assertThat(state.isSuccess).isFalse()
        assertThat(state.error).isEqualTo("Invalid credentials")
    }


    @Test
    fun `When process login with invalid credentials Then return login failed`() = runTest {

        val newUsername = "newuser@example.com"
        val password = "password123"
        // Arrange
        val errorMessage = "Login failed due to invalid credentials"
        `when`(mockLoginShared.getString("usuario")).thenReturn("")

        `when`(mockAuth.signInWithEmailAndPassword(newUsername, password))
            .thenThrow(RuntimeException(errorMessage))
        `when`(mockAuth.createUserWithEmailAndPassword(newUsername, password))
            .thenThrow(RuntimeException(errorMessage))


        // Act
        viewModel.onUsernameChange(newUsername)
        viewModel.onPasswordChange(password)

        assertThat(viewModel.login()).isFalse()


        // Assert
        assertThat(viewModel.state.first().error).isEqualTo(errorMessage)
        assertThat(viewModel.state.value.isSuccess).isFalse()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}