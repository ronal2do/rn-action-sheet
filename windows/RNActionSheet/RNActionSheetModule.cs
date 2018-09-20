using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Toast.RNActionSheet
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNActionSheetModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNActionSheetModule"/>.
        /// </summary>
        internal RNActionSheetModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNActionSheet";
            }
        }
    }
}
