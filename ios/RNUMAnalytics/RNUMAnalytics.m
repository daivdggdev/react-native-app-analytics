//
//  RNUMAnalytics.m
//  RNUMAnalytics
//
//  Created by dwwang on 2017/9/20.
//  Copyright © 2017年 dwwang. All rights reserved.
//

#import "RNUMAnalytics.h"

@implementation RNUMAnalytics

RCT_EXPORT_MODULE();

+ (dispatch_queue_t)sharedMethodQueue
{
    static dispatch_queue_t methodQueue;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        methodQueue = dispatch_queue_create("react-native-umeng-analytics", DISPATCH_QUEUE_SERIAL);
    });
    return methodQueue;
}

- (dispatch_queue_t)methodQueue
{
    return [RNUMAnalytics sharedMethodQueue];
}

RCT_EXPORT_METHOD(addEvent:(NSString *)name location:(NSString *)location)
{
    RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
}

RCT_EXPORT_METHOD(setUmSocialAppkey:(NSString*)appKey)
{
    [[UMSocialManager defaultManager] setUmSocialAppkey:appKey];
}

- (NSDictionary *)constantsToExport
{
    UMSocialManager *manager = [UMSocialManager defaultManager];
    return @{
             @"isWXSupport": [NSNumber numberWithBool:[manager isSupport:UMSocialPlatformType_WechatSession]],
             @"isQQSupport": [NSNumber numberWithBool:[manager isSupport:UMSocialPlatformType_QQ]],
             @"isSinaSupport": [NSNumber numberWithBool:[manager isSupport:UMSocialPlatformType_Sina]],
             };
}

@end
