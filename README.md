# Lou Sizzo Devs
## _Kotlin - Login Firebase with Gmail and email / ViewBinding and Glide_
### Algo breve
Muchas veces necesitamos tener acceso a una app sin tener base de datos, esta herramienta nos la proporciona Firebase para la autenticación
al momento de acceder

### Descripción

Esta pequeña app te ayudará a crear un login con acceso mediante Gmail o crear un usuario con correo y contraseña, ademas de recuperar la contraseña 
en caso de olvido.

Para que esto funcione de manera adecuada, tendras que darle acceso a la atenticación desde Authentication->Sign-in method en tu consola de Firebase
en la opcion **Proveedores de acceso** hay que habilitar **Google** y **Correo electrónico/contraseña**. En el caso de acceso con Google hay que copiar
el **ID de cliente web** y sustituir el que se encuentra en el archivo strings.xml. 

NOTA: Para que Firebase auth funcione a la perfección tienes que generar el archivo _google-services.json_ que te da Firebase al crear un nuevo
proyecto para Android e insertarlo en la carpeta ***app***, se tiene que registrar la SHA-1 y el SHA-256, esto lo puedes lograr con la opción de gradle, en la parte
superior derecha de la ventana de Android Studio con el icono del elefante y ejecutando el comando: _signingreport_, esto te generará ambos y los podras registrar en tu consola de Firebase
# 
### Languages and Tools used:

<img src="https://miro.medium.com/max/360/1*e3UJ-N8TPw8zGUn9cYzaJg.png" width="100" height="100" title="Kotlin" style="padding:20px;"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Android_Studio_Icon_%282014-2019%29.svg/1200px-Android_Studio_Icon_%282014-2019%29.svg.png" width="100" height="100"  title="Android Studio" style="padding:20px;">


#
### Screenshots
<img src="https://github.com/lordsizzo/Android_LoginFirebaseKotlin/blob/master/Screenshot_20220203-094038_Login%20With%20FirebaseKotlin.jpg" width="200" height="400" style="padding:20px;"> <img src="https://github.com/lordsizzo/Android_LoginFirebaseKotlin/blob/master/Screenshot_20220203-094045_Google%20Play%20services.png" width="200" height="400" style="padding:20px;"> <img src="https://github.com/lordsizzo/Android_LoginFirebaseKotlin/blob/master/Screenshot_20220203-094053_Login%20With%20FirebaseKotlin.png" width="200" height="400" style="padding:20px;"> <img src="https://github.com/lordsizzo/Android_LoginFirebaseKotlin/blob/master/Screenshot_20220203-094101_Login%20With%20FirebaseKotlin.png" width="200" height="400" style="padding:20px;">

# 
### Contacto

<img src="https://www.pinclipart.com/picdir/big/150-1504080_facebook-white-facebook-white-icon-png-2018-clipart.png" width="20" height="20"  title="Facebook"> [@lou.sizzo.development](https://www.facebook.com/lou.sizzo.development "@lou.sizzo.development")

<img src="https://toppng.com/public/uploads/thumbnail/subscribe-to-our-mailing-list-icono-de-instagram-en-blanco-11562863465psekvjyxmv.png" width="20" height="20"  title="Instragram"> [@lou_sizzo](http://instagram.com/lou_sizzo "@lou_sizzo")

<img src="https://www.pikpng.com/pngl/b/31-313145_twitter-png-white-white-twitter-logo-no-background.png" width="20" height="20"  title="Twitter"> [@lord_sizzo](https://twitter.com/lord_sizzo "@lord_sizzo")

![](https://visitor-badge.laobi.icu/badge?page_id=lordsizzo.Android_LoginFirebaseKotlin)
[![Github](https://img.shields.io/github/followers/lordsizzo?label=Follow&style=social)](https://github.com/lordsizzo)


