/*package com.baben.apps.appformation3

import com.baben.apps.appformation3.core.app.AuthLocalStorage

import android.content.SharedPreferences
import org.mockito.Mock
import android.content.Context
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner



  private lateinit var authLocalStorage: AuthLocalStorage

   @Mock
   private lateinit var sharedPreferences: SharedPreferences

   @Mock
   private lateinit var editor: SharedPreferences.Editor

   @Before
   fun setup() {
       MockitoAnnotations.initMocks(this)
       val context = mock(Context::class.java)
       `when`(context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)).thenReturn(sharedPreferences)
       `when`(sharedPreferences.edit()).thenReturn(editor)

       authLocalStorage = AuthLocalStorage(context)
   }

   @Test
   fun testSaveToken() {
       authLocalStorage.saveToken("test_token")
       verify(editor).putString("token", "test_token")
       verify(editor).apply()
   }

   @Test
   fun testGetToken() {
       `when`(sharedPreferences.getString("token", null)).thenReturn("test_token")
       assertEquals("test_token", authLocalStorage.getToken())
   }

   @Test
   fun testIsLoggedIn() {
       `when`(sharedPreferences.contains("token")).thenReturn(true)
       assertTrue(authLocalStorage.isLoggedIn())

       `when`(sharedPreferences.contains("token")).thenReturn(false)
       assertFalse(authLocalStorage.isLoggedIn())
   }*/
package com.baben.apps.appformation3
import com.baben.apps.appformation3.core.app.AuthLocalStorage
import android.content.Context
import android.content.SharedPreferences
import com.baben.apps.appformation3.core.app.AuthLocalStorage.Companion.DEFAULT_TOKEN_VALUE
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner





@RunWith(MockitoJUnitRunner::class)
class AuthLocalStorageTest {

    companion object{
        private const val TOKEN : String = "myToken"
    }

    private lateinit var sut : AuthLocalStorage

    @Mock
    lateinit var contextMock : Context
    @Mock
    lateinit var shardPreferenceMock : SharedPreferences
    @Mock
    lateinit var editorMock : SharedPreferences.Editor



    @Before
    fun setUp(){
        Mockito.`when`(contextMock.getSharedPreferences(anyString(), anyInt())).thenReturn(shardPreferenceMock)
        Mockito.`when`(shardPreferenceMock.edit()).thenReturn(editorMock)
        Mockito.`when`(editorMock.putString(anyString(), any())).thenReturn(editorMock)
        Mockito.`when`(shardPreferenceMock.getString(anyString(), any())).thenReturn(TOKEN)
        sut = AuthLocalStorage(contextMock)
    }

    @Test
    fun test_shardPreference_initialized(){
        //Given
        val keyCaptor = ArgumentCaptor.forClass(String::class.java)
        val modeCaptor = ArgumentCaptor.forClass(Int::class.java)
        //When
        //Then
        verify(contextMock).getSharedPreferences(keyCaptor.capture(),modeCaptor.capture())
        Assert.assertNotNull(shardPreferenceMock)
        val expectedKey : String = keyCaptor.value
        val expectedMode : Int = modeCaptor.value
        Assert.assertEquals(expectedKey , AuthLocalStorage.SHARD_PREFERENCE_KEY)
        Assert.assertEquals(expectedMode , Context.MODE_PRIVATE)
    }

    @Test
    fun test_saveToken_tokenSaved(){
        //Given
        val tokenCaptor = ArgumentCaptor.forClass(String::class.java)
        //When
        sut.saveToken(TOKEN)
        //Then
        verify(shardPreferenceMock).edit()
        verify(editorMock).putString(tokenCaptor.capture(), tokenCaptor.capture())
        verify(editorMock).apply()
        val expectedKey : String = tokenCaptor.allValues[0]
        val expectedValue : String = tokenCaptor.allValues[1]
        Assert.assertEquals(expectedKey,AuthLocalStorage.TOKEN_KEY)
        Assert.assertEquals(expectedValue, TOKEN)
    }


    @Test
    fun test_getToken_withTokenNotSaved(){
        //Given
        val tokenCaptor = ArgumentCaptor.forClass(String::class.java)
        Mockito.`when`(shardPreferenceMock.getString(anyString(), any())).thenReturn(AuthLocalStorage.DEFAULT_TOKEN_VALUE)
        //When
        val result : String = sut.getToken()
        //Then
        verify(shardPreferenceMock).getString(tokenCaptor.capture(), tokenCaptor.capture())
        val expectedKey : String = tokenCaptor.allValues[0]
        val expectedDefaultValue : String = tokenCaptor.allValues[1]
        Assert.assertEquals(expectedKey,AuthLocalStorage.TOKEN_KEY)
        Assert.assertEquals(expectedDefaultValue, AuthLocalStorage.DEFAULT_TOKEN_VALUE)
        Assert.assertEquals(result, AuthLocalStorage.DEFAULT_TOKEN_VALUE)
    }

    @Test
    fun test_getToken_withTokenSaved(){
        //Given
        val tokenCaptor = ArgumentCaptor.forClass(String::class.java)
       // When
        sut.saveToken(TOKEN)
        val result : String = sut.getToken()
        //Then
        verify(shardPreferenceMock).getString(tokenCaptor.capture(), tokenCaptor.capture())
        val expectedKey : String = tokenCaptor.allValues[0]
        val expectedDefaultValue : String = tokenCaptor.allValues[1]
        Assert.assertEquals(expectedKey,AuthLocalStorage.TOKEN_KEY)
        Assert.assertEquals(expectedDefaultValue, AuthLocalStorage.DEFAULT_TOKEN_VALUE)
        Assert.assertEquals(result, TOKEN)
    }

    @Test
    fun test_clearToken_tokenShouldBeRemoved(){
        //Given
        val tokenCaptor = ArgumentCaptor.forClass(String::class.java)
        Mockito.`when`(editorMock.remove(anyString())).thenReturn(editorMock)
        Mockito.`when`(shardPreferenceMock.getString(anyString(), any())).thenReturn(DEFAULT_TOKEN_VALUE)
        //When
        sut.saveToken(TOKEN)
        sut.clearToken()
        val result : String = sut.getToken()
        //Then
        verify(shardPreferenceMock).edit()
        verify(editorMock).remove(tokenCaptor.capture())
        verify(editorMock, times(2)).apply()
        val expectedKey = tokenCaptor.value
        Assert.assertEquals(result, DEFAULT_TOKEN_VALUE)
        Assert.assertEquals(expectedKey, AuthLocalStorage.TOKEN_KEY)
    }

    @Test
    fun test_isLogged_withTokenNotSaved(){
        //Given
        Mockito.`when`(shardPreferenceMock.getString(anyString(), any())).thenReturn(DEFAULT_TOKEN_VALUE)
        //When
        val result : Boolean = sut.isLogged()
        //Then
        verify(shardPreferenceMock).getString(anyString(), anyString())
        Assert.assertFalse(result)
    }

    @Test
    fun test_isLogged_withTokenSaved(){
        //Given
        //When
        sut.saveToken(TOKEN)
        val result : Boolean = sut.isLogged()
        //Then
        verify(shardPreferenceMock).getString(anyString(), anyString())
        Assert.assertTrue(result)
    }



}
/*@RunWith(MockitoJUnitRunner::class)

class AuthLocalStorageTest{
    companion object {
        private const val token="test_token"

    }
       @Mock
       lateinit var AuthLocalStorage:AuthLocalStorage
       @Mock
       lateinit var context:Context
       @Mock
       lateinit var SharedPreferencesMock:SharedPreferences
       @Before
       fun setUp(){
          AuthLocalStorage= AuthLocalStorage(context)
           `when`(SharedPreferencesMock.getString(anyString(), any())).thenReturn(token)
            `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(SharedPreferencesMock)
       }
       @Test
       //vérifier si la méthode getSharedPreferences est appelée avec les bons arguments lors de l'initialisation de AuthLocalStorage.
       fun test_intaialization_sharedpreferences(){
           //Given,when,then
           //Given:C'est la partie où vous configurez l'état initial nécessaire pour le test
           val clecaptor=ArgumentCaptor.forClass(String::class.java)
           val modecaptor=ArgumentCaptor.forClass(Int::class.java)
           //when:C'est la partie où vous appelez la méthode ou l'action que vous voulez tester
           //then:C'est la partie où vous vérifiez le résultat de l'action ou de la méthode appelée dans le "When".
           verify(context).getSharedPreferences(anyString(), anyInt())
           Assert.assertNotNull(SharedPreferencesMock)
           val Expectedcle:String=clecaptor.capture()
           val Expectedmode: Int =modecaptor.capture()
           Assert.assertEquals(Expectedcle,AuthLocalStorage.Sharedpreferencekey)
           Assert.assertEquals(Expectedmode,Context.MODE_PRIVATE)
       }
       @Test
       fun test_savetoken(){
           //Given,when,then testiw wach tsava wlla la
           //Given :token
           val tokencaptor=ArgumentCaptor.forClass(String ::class.java)
           //when
           AuthLocalStorage.saveToken()
           //then

       }*/


