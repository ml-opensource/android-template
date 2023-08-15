# Design System

A design system is a comprehensive set of design elements, principles, and guidelines that provide a framework for defining and governing the overall visual and user interface (UI) design of a product or service. In software development, particularly in mobile app development, a design system typically includes a library of standardized UI components, themes, and styles that serve as the building blocks for creating consistent and cohesive UI screens.

 Essentially, a design system represents the implementation of the design decisions made by designers, translated into code, and applied throughout the entire product or service. 





## Approach

In most cases, the very default design system on Android implements Material Design. That's how get our basic look for the Buttons, Top Bars, and behavior components like Bottom Sheets. While it is easy to use and modify, in most scenarios the Design System we, as developers, have to implement will be very different. It can have more colors, more text styles, and overall be very far from the Material Design APIs.

As it doesn’t make much sense to implement a completely custom design system, the designsystem in the template serves as a bridge between Material Design and the specific Design System we have to implement. This is done by having custom Typography, Colors, and Dimensions classes that ultimately get translated into Material Design. This provides the following benefits:

- Our colors and typography don't need to follow Material Guidelines. We can more or fewer colors depending on the actual design, and different typography 

- We can have custom properties hooked up with the Theme, which the normal Material Design System doesn’t provide, for example, Dimensions

- We can still use components from the Material Theme, like Buttons, top bars, Cards, and Surfaces. And, given the custom colors, fonts were translated properly (for example custom cardBackgroundColor will be translated into surface color in material) they will work out of the box



## Structure

Currently, the structure of the design system is following

- `designsystem`
  - `components`  - ui components, some may have separate packages 
  - `theme` - contains theme-related information
    - `colors` - Colors that define design-system
    - `typograpy` - Text styles that define design-system
  - `AppTheme.kt `

Develops are free to add additional packages if they want to

## Best practices

### Components

- **Generic and Distinct Naming**. Implement re-usable design system components in the Design system and make the name of these components. Since Material has its own Buttons and TopBars, the name of your components should be unique enough so you don't confuse the imports. For example AppButton , AppTopBar  

- **Make the API of your components as small and concise as possible.** Material components are very flexible and they all support Slot APIs, for example, you can put any @Composable into `material.Button` content property. This is great, but in most cases, all you need for such a button is a simple text as String. 

```kotlin
// GOOD
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    type: AppButtonType = AppButtonType.Filled,

)
```


- **Don’t reference your Domain.** The design system should not know about your domain models or render something specific to the features. They should stay as generic and reusable as possible. That goes both for the Component name and its implementation

- **Extract complex components away from the features.** In case you have a custom component for some feature (fx some fancy-looking Slider), move all its complexity to one of the shared modules/packages (design system or core.ui) and make it generic. 



### Colors and Typography

- **Expand and/or Shrink the AppTypography and AppColors to suit your needs.** Make it mirror the actual design system you are working with and then convert it to Material where it's possible. 

- **Don't hardcode colors.**  While colors can be very tempting to hardcode always try to see if this color can fit into your design system somehow. Maybe its the same background color but with different alpha?

- **Use generic color names, not actual colors.** Use names like background  primary and error instead of actual colors like White Blue and Red.  This will make it easy to adapt your design system and use different Theme types, like Dark Mode, or in case the app will go through White-labeling

- **Don’t hardcode text styles.** This is mainly a concern for the properties that involve numbers: letterSpacing , fontSize lineHeight and etc. Always use your design system typography to reference that. On the other hand, making text bold is Ok  .

```kotlin
// OK
val headline3 = Theme.typography.headline3
val headline3Bold = Theme.typography.headline3.copy(fontWeight = FontWeight.Bold)

// Not ok
val body2 = Theme.typography.body1.copy(fontSize = 12.sp)
```