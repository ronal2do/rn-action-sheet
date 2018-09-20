import { ActionSheetIOS } from 'react-native';

const BottomSheetIOS = {
  show: (options, callback) => {
    return ActionSheetIOS.showActionSheetWithOptions(options, callback);
  },

  showShare: (options, failureCallback, successCallback) => {
    return ActionSheetIOS.showShareActionSheetWithOptions(options, failureCallback, successCallback);
  }
}

export default BottomSheetIOS;