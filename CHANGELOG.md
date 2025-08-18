## [2.1]

### Added
- Product image in item layout using `ImageView`
- Custom font resources in `res/font/`
- Sample data support in layout previews (images and text)

### Changed
- Updated UI resources to support multiple screen densities with appropriate drawables

## [2.2]

### Added
- Material Design components: `MaterialCardView`
- Currency formatting using `NumberFormat` for product prices
- Guidelines in `ConstraintLayout` for layout alignment
- Percentage-based dimensions for responsive UI scaling

## [2.3]

### Added
- TextInputLayout to enhance EditText fields in product form
- AlertDialog with support for title, message, and action buttons
- Custom layouts for dialogs using `setView()`

## [2.4]

### Added
- Coil library integration for image loading
- Internet permission configured in `AndroidManifest.xml`
- Image URL property added to the product model
- Coil used in both product form and RecyclerView adapter
- GIF support configured in Coil using a custom `ImageLoader`

## [2.5]

### Added
- Coil customization: placeholder, fallback and error handling
- Delegation pattern to isolate dialog responsibilities
- Support for high-order function listener in image dialog
- Input validation for product fields in form
- Theme customization: primary colors, fonts and app bar title

## [2.6]

### Added
- Product details screen (`ProductDetailsActivity`)
- Parcelable implementation using `@Parcelize`
- Constant key for safe data transfer between Activities
- Locale-aware BigDecimal extension using `Locale.forLanguageTag`

### Changed
- Image loading function now supports custom placeholder, error and fallback
- Activity theme customized to hide ActionBar on details screen
- Updated `getParcelableExtra()` to new API with backward compatibility

## [3.1]

### Added
- Room persistence setup with entity `ProductEntity`
- DAO interface with insert and query methods
- Database configuration using `@Database` annotation
- Type converters for complex data types
- Query autocompletion and syntax highlighting in Android Studio

### Changed
- Product data is now persisted in SQLite via Room instead of in-memory storage

## [3.2]

### Added
- Database instance creation with `Room.databaseBuilder()`
- Support for inserting and retrieving products directly from Room
- Integration of Room with app flow (form and product list now use persisted data)
- Database debugging with Android Studio App Inspector

### Changed
- Removed in-memory DAO implementation in favor of Room persistence
- Room configured to allow main thread queries for didactic purposes
- Schema generation warning suppressed by configuring schema location or disabling schema export

## [3.3]

### Added
- Options menu implementation in `Activity` using XML and menu inflater
- Support for menu items with titles and icons
- Listener handling for menu item clicks
- Popup menu implementation on product item selection
