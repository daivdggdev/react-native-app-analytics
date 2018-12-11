//
//  RNUMAnalytics.m
//  RNUMAnalytics
//
//  Created by dwwang on 2017/9/20.
//  Copyright © 2017年 dwwang. All rights reserved.
//

#import "RNUMAnalytics.h"
#import <UMCommon/UMCommon.h>
#import <UMAnalytics/MobClick.h>

@implementation RNUMAnalytics

RCT_EXPORT_MODULE();

+ (dispatch_queue_t)sharedMethodQueue
{
    static dispatch_queue_t methodQueue;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        methodQueue = dispatch_queue_create("react-native-app-analytics", DISPATCH_QUEUE_SERIAL);
    });
    return methodQueue;
}

- (dispatch_queue_t)methodQueue
{
    return [RNUMAnalytics sharedMethodQueue];
}

RCT_EXPORT_METHOD(startWithConfigure:(NSString*)appKey channelId:(NSString*)channelId)
{
    [UMConfigure initWithAppkey:appKey channel:channelId];
}

RCT_EXPORT_METHOD(onEvent:(NSString*)eventId)
{
    [MobClick event:eventId];
}

@end
