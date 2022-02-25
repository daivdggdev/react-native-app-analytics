require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = "react-native-app-analytics"
  s.version      = package['version'].gsub(/v|-beta/, '')
  s.summary      = package['description']
  s.author       = package['author']
  s.license      = package['license']
  s.homepage     = package['homepage']
  s.platform     = :ios, "8.0"
  s.source       = { :git => "https://github.com/daivdggdev/react-native-app-analytics", :tag => "v#{s.version}" }
  s.source_files = "ios/RNUMAnalytics/*.{h,m}"

  s.dependency 'React'
  s.dependency 'UMCommon'
  s.dependency 'UMDevice'
end
