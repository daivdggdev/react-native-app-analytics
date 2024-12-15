'use strict';

import { Platform } from 'react-native';

var RNUMAnalytics = require('react-native').NativeModules.RNUMAnalytics;
module.exports = {
  getChannel: function() {
    return RNUMAnalytics.getChannel();
  },

  setLogEnabled: function(enabled) {
    return RNUMAnalytics.setLogEnabled(enabled);
  },

  preInit: function(appKey, channelId) {
    if (Platform.OS === 'ios') {
      return;
    }
    return RNUMAnalytics.preInit(appKey, channelId);
  },

  startWithConfigure: function(appKey, channelId) {
    return RNUMAnalytics.startWithConfigure(appKey, channelId);
  },

  onEvent: function(eventId) {
    return RNUMAnalytics.onEvent(eventId);
  }
};
