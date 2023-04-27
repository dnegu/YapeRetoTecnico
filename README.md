# YapeRecipes
_____________________________

## Descripción
YapeRecipes es un aplicativo de recetas el cual muestra un listado de recetas, al seleccionar cualquiera de ellas, te dirige al detalle de la misma, en donde podrá encontrar un boton para redirigir al lugar de origen de la receta.

## Descripción Tecnica

- El aplicativo esta desarrollado en Kotlin con el **Android Studio**.
- Para la parte visual de las vistas se desarrollo con **JetPackCompose**.
- Se utilizo **Clean Architecture**.
- Como arquitectura se utilizo **MVVM**.
- Para la API se utilizó https://www.mockable.io/.

## Decisiones tomadas

En el anterior punto se detallo sobre las tecnologías que se utilizo para el desarrollo del aplicativo, ahora explicaré un poco más sobre las decisiones tomadas.

-- Comenzando se utilizo Kotlin porque es el Lenguaje de Programación que Google preferido para desarrollar aplicaciones nativas, además que constantenmente da soporte a las ventajas que ofrece el lenguaje, como la eliminación de los NullPointerExcepcion, Corutinas, multiplataforma, etc.

-- Segundo, se escogio JetPackCompose, por que personalmente vi desarrollos con SwiftUI y el poder que le da la programación declarativa a las UI es enorme, además que se puede reutilizar codigo.

-- Tercero, se escogio la Clean Architecture ya que organiza mejor los paquetes que vamos a utilizar, además que apoya al principio SOLID.

-- Cuarto, arquitectura MVVM esta elección al igual que el lenguaje de programación es lo que Google nos alienta a utilizar, además que si se reatilza correctamente es muy robusta.

-- Por utlimo, se utilizo mockable.io, por la facilidad para crear endpoints que se consumirá en el aplicativo.

## Librerias

-- Para el desarrollo con compose se agrego librerias *compose* para las vistas, navegación, runtime, activity, coil(Para mostrar imagenes con url), testing y diseño con material3(Esta librería ofrece mejoras con Compose).

-- Para el mapa se utilizo las librerias de google-maps y google-play-service.

-- Como inyector de dependencias se utilizó Hilt, pero su version para compose navigation, 

-- Para el consumo del endpoint se utilizo Retrofit.

-- Por ultimo, para la implementación de test de UI se utilizo ui-tooling, ui-test-manifest, ui-test-junit4 en sus versiones para compose.

## Mejoras

Aunque el aplicativo cumple con los requerimientos, se puede implementar las siguientes mejoras.

-- Implementar Room para la persistencia de datos localmente.
-- Implementación de TestUnitarios(No solo los de UI).
-- Implementación de MultiLanguage.
-- Se puede utilizar ML Kit para traducir las recetas, de ese modo pueda abarcar más usuarios.
-- Se puede utilizar ChatGPT para buscar recetas que no se encuentren en el api, como en el siguiente enlace: https://www.griproom.com/fun/how-to-use-chatgpt-to-generate-recipes.

## Agradecimiento

Igualmente estoy agradecido de haber pertenecido a este proceso de selección y me gustaría que por favor me dieran un feedback, de ese modo pueda seguir mejorando en el desarrollo de mi codigo.

***Sin más que decir, Gracias!!***