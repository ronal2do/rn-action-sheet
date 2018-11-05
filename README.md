
# rn-action-sheet

Android package https://github.com/soarcn/BottomSheet

## Getting started

`$ npm install rn-action-sheet --save`

### Mostly automatic installation

`$ react-native link rn-action-sheet`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `rn-action-sheet` and add `RNActionSheet.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNActionSheet.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.ronal2do.RNActionSheetPackage;` to the imports at the top of the file
  - Add `new RNActionSheetPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':rn-action-sheet'
  	project(':rn-action-sheet').projectDir = new File(rootProject.projectDir, 	'../node_modules/rn-action-sheet/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':rn-action-sheet')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNActionSheet.sln` in `node_modules/rn-action-sheet/windows/RNActionSheet.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Toast.RNActionSheet;` to the usings at the top of the file
  - Add `new RNActionSheetPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNActionSheet from 'rn-action-sheet';

onPress = () => RNActionSheet.show({
  options: ['Banana', 'Orange', 'Apple', 'Mango', 'Lemon', 'Cancel'],
  title: 'Mix Salad',
  dark: this.state.dark, // boolean
  showCancel: true, // android only, to show cancel button
  cancelButtonIndex: 5,
}, value => {
  console.log(value);
})

onShare = () => RNActionSheet.showShare({
  url: 'https://your_url.com',
  subject: 'Share',
  message: 'Simple share',
}, (value) => {
  alert(value);
}, (resultcode, path) => {
    console.log(resultcode);
    console.log(path);
})

```
  