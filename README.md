# 工程简介
spring security 的demo
1. 使用了jwt和redis做缓存实现基本的认证功能
2. 鉴权
3. 认证和授权失败处理
4. 跨域
5. 自定义权限校验 
# The difference between AuthenticationEntryPoint and AuthenticationFailureHandler is
# that the former is used to "tell" unauthenticated users where to authenticate, 
# for example, by redirecting them to a login form. The latter is used to handle bad 
# login attempts.
# 延伸阅读

