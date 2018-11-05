declare module 'rn-action-sheet' {
  export function show(
    options: Options,
    callback?: (error?: any) => void
  ): void;

  export function showShare(
    options: Options,
    failureCallback?: (error?: any) => void,
    successCallback?: (error?: any) => void,
  ): void;

  export interface Share {
    url: String;
    subject: String;
    message: String;
  }

  export interface Options {
    options: [String];
    title: String;
    dark: boolean;
    showCancel: boolean;
    cancelButtonIndex: number;
  }
}