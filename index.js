'use strict';

var RNUMAnalytics = require('react-native').NativeModules.RNUMAnalytics;
module.exports = {
  startWithConfigure: function(appKey, channelId) {
    return RNUMAnalytics.startWithConfigure(appKey, channelId);
  },

  onEvent: function(eventId) {
    return RNUMAnalytics.onEvent(eventId);
  }
};
