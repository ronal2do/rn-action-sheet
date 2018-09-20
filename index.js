
import { NativeModules, Platform } from 'react-native'
import RNActionSheetIOS from './RNActionSheet.ios'

const { RNActionSheet } = NativeModules
export default Platform.OS === 'ios' ? RNActionSheetIOS : RNActionSheet

