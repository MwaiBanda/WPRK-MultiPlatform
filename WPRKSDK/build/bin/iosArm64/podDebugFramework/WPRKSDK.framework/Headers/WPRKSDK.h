#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class WPRKSDKKoin_coreKoin, WPRKSDKFirebase_authAuthResult, WPRKSDKFirebase_authAuthCredential, WPRKSDKKotlinUnit, WPRKSDKFirebase_authFirebaseAuth, WPRKSDKKoin_coreKoinApplication, WPRKSDKKoin_coreModule, WPRKSDKKoin_coreScope, WPRKSDKKoin_coreParametersHolder, WPRKSDKKotlinLazyThreadSafetyMode, WPRKSDKKoin_coreLogger, WPRKSDKKoin_coreInstanceRegistry, WPRKSDKKoin_corePropertyRegistry, WPRKSDKKoin_coreScopeRegistry, WPRKSDKKotlinThrowable, WPRKSDKKotlinArray<T>, WPRKSDKKotlinException, WPRKSDKKotlinRuntimeException, WPRKSDKKotlinIllegalStateException, FIRAuthDataResult, WPRKSDKFirebase_authFirebaseUser, FIRAuthCredential, WPRKSDKFirebase_authActionCodeResult, WPRKSDKFirebase_authActionCodeSettings, FIRAuth, WPRKSDKKoin_coreKoinApplicationCompanion, WPRKSDKKoin_coreLevel, WPRKSDKKoin_coreInstanceFactory<T>, WPRKSDKKotlinPair<__covariant A, __covariant B>, WPRKSDKKoin_coreScopeDSL, WPRKSDKKoin_coreSingleInstanceFactory<T>, WPRKSDKKoin_coreParametersHolderCompanion, WPRKSDKKotlinEnumCompanion, WPRKSDKKotlinEnum<E>, WPRKSDKKoin_coreScopeRegistryCompanion, WPRKSDKFirebase_authAuthTokenResult, WPRKSDKFirebase_authPhoneAuthCredential, FIRUser, WPRKSDKFirebase_authUserMetaData, WPRKSDKFirebase_authMultiFactor, WPRKSDKFirebase_authUserInfo, WPRKSDKFirebase_authAndroidPackageName, WPRKSDKKoin_coreBeanDefinition<T>, WPRKSDKKoin_coreInstanceFactoryCompanion, WPRKSDKKoin_coreInstanceContext, FIRAuthTokenResult, FIRPhoneAuthCredential, FIRUserMetadata, FIRMultiFactor, WPRKSDKFirebase_authMultiFactorAssertion, WPRKSDKFirebase_authMultiFactorSession, WPRKSDKFirebase_authMultiFactorInfo, WPRKSDKKoin_coreKind, WPRKSDKKoin_coreCallbacks<T>, FIRMultiFactorAssertion, FIRMultiFactorSession, FIRMultiFactorInfo;

@protocol WPRKSDKKoin_coreKoinComponent, WPRKSDKAuthRepository, WPRKSDKKoin_coreKoinScopeComponent, WPRKSDKKoin_coreQualifier, WPRKSDKKotlinKClass, WPRKSDKKotlinLazy, WPRKSDKKotlinx_coroutines_coreFlow, WPRKSDKKoin_coreScopeCallback, WPRKSDKKotlinKDeclarationContainer, WPRKSDKKotlinKAnnotatedElement, WPRKSDKKotlinKClassifier, WPRKSDKKotlinComparable, WPRKSDKKotlinIterator, WPRKSDKKotlinx_coroutines_coreFlowCollector, FIRUserInfo;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface WPRKSDKBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface WPRKSDKBase (WPRKSDKBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface WPRKSDKMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface WPRKSDKMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorWPRKSDKKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface WPRKSDKNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface WPRKSDKByte : WPRKSDKNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface WPRKSDKUByte : WPRKSDKNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface WPRKSDKShort : WPRKSDKNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface WPRKSDKUShort : WPRKSDKNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface WPRKSDKInt : WPRKSDKNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface WPRKSDKUInt : WPRKSDKNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface WPRKSDKLong : WPRKSDKNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface WPRKSDKULong : WPRKSDKNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface WPRKSDKFloat : WPRKSDKNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface WPRKSDKDouble : WPRKSDKNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface WPRKSDKBoolean : WPRKSDKNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Greeting")))
@interface WPRKSDKGreeting : WPRKSDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)greeting __attribute__((swift_name("greeting()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Platform")))
@interface WPRKSDKPlatform : WPRKSDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (readonly) NSString *platform __attribute__((swift_name("platform")));
@end;

__attribute__((swift_name("Koin_coreKoinComponent")))
@protocol WPRKSDKKoin_coreKoinComponent
@required
- (WPRKSDKKoin_coreKoin *)getKoin __attribute__((swift_name("getKoin()")));
@end;

__attribute__((swift_name("AuthController")))
@interface WPRKSDKAuthController : WPRKSDKBase <WPRKSDKKoin_coreKoinComponent>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("empty")))
@interface WPRKSDKempty : WPRKSDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("AuthRepository")))
@protocol WPRKSDKAuthRepository
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)loginAsGuestWithCompletionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("loginAsGuest(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)loginWithCredentialCredential:(WPRKSDKFirebase_authAuthCredential *)credential completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("loginWithCredential(credential:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)loginWithEmailAndPasswordEmail:(NSString *)email password:(NSString *)password completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("loginWithEmailAndPassword(email:password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)resetPasswordEmail:(NSString *)email completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("resetPassword(email:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)signupWithEmailAndPasswordEmail:(NSString *)email password:(NSString *)password completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("signupWithEmailAndPassword(email:password:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AuthRepositoryImpl")))
@interface WPRKSDKAuthRepositoryImpl : WPRKSDKBase <WPRKSDKAuthRepository>
- (instancetype)initWithAuth:(WPRKSDKFirebase_authFirebaseAuth *)auth __attribute__((swift_name("init(auth:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)loginAsGuestWithCompletionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("loginAsGuest(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)loginWithCredentialCredential:(WPRKSDKFirebase_authAuthCredential *)credential completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("loginWithCredential(credential:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)loginWithEmailAndPasswordEmail:(NSString *)email password:(NSString *)password completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("loginWithEmailAndPassword(email:password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)resetPasswordEmail:(NSString *)email completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("resetPassword(email:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)signupWithEmailAndPasswordEmail:(NSString *)email password:(NSString *)password completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("signupWithEmailAndPassword(email:password:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LoginWithEmailAndPasswordUseCase")))
@interface WPRKSDKLoginWithEmailAndPasswordUseCase : WPRKSDKBase
- (instancetype)initWithAuthRepository:(id<WPRKSDKAuthRepository>)authRepository __attribute__((swift_name("init(authRepository:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeEmail:(NSString *)email password:(NSString *)password onCompletion:(void (^)(WPRKSDKFirebase_authAuthResult *))onCompletion completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(email:password:onCompletion:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DependencyRegistryKt")))
@interface WPRKSDKDependencyRegistryKt : WPRKSDKBase
+ (WPRKSDKKoin_coreKoinApplication *)doInitKoin __attribute__((swift_name("doInitKoin()")));
+ (WPRKSDKKoin_coreKoinApplication *)doInitKoinAppDeclaration:(void (^)(WPRKSDKKoin_coreKoinApplication *))appDeclaration __attribute__((swift_name("doInitKoin(appDeclaration:)")));
@property (class, readonly) WPRKSDKKoin_coreModule *repositoryModule __attribute__((swift_name("repositoryModule")));
@property (class, readonly) WPRKSDKKoin_coreModule *singletonModule __attribute__((swift_name("singletonModule")));
@property (class, readonly) WPRKSDKKoin_coreModule *useCasesModule __attribute__((swift_name("useCasesModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoin")))
@interface WPRKSDKKoin_coreKoin : WPRKSDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)close __attribute__((swift_name("close()")));
- (void)createEagerInstances __attribute__((swift_name("createEagerInstances()")));
- (WPRKSDKKoin_coreScope *)createScopeT:(id<WPRKSDKKoin_coreKoinScopeComponent>)t __attribute__((swift_name("createScope(t:)")));
- (WPRKSDKKoin_coreScope *)createScopeScopeId:(NSString *)scopeId __attribute__((swift_name("createScope(scopeId:)")));
- (WPRKSDKKoin_coreScope *)createScopeScopeId:(NSString *)scopeId source:(id _Nullable)source __attribute__((swift_name("createScope(scopeId:source:)")));
- (WPRKSDKKoin_coreScope *)createScopeScopeId:(NSString *)scopeId qualifier:(id<WPRKSDKKoin_coreQualifier>)qualifier source:(id _Nullable)source __attribute__((swift_name("createScope(scopeId:qualifier:source:)")));
- (void)declareInstance:(id _Nullable)instance qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier secondaryTypes:(NSArray<id<WPRKSDKKotlinKClass>> *)secondaryTypes allowOverride:(BOOL)allowOverride __attribute__((swift_name("declare(instance:qualifier:secondaryTypes:allowOverride:)")));
- (void)deletePropertyKey:(NSString *)key __attribute__((swift_name("deleteProperty(key:)")));
- (void)deleteScopeScopeId:(NSString *)scopeId __attribute__((swift_name("deleteScope(scopeId:)")));
- (id _Nullable)getClazz:(id<WPRKSDKKotlinKClass>)clazz qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(clazz:qualifier:parameters:)")));
- (id)getQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(qualifier:parameters:)")));
- (NSArray<id> *)getAll __attribute__((swift_name("getAll()")));
- (WPRKSDKKoin_coreScope *)getOrCreateScopeScopeId:(NSString *)scopeId __attribute__((swift_name("getOrCreateScope(scopeId:)")));
- (WPRKSDKKoin_coreScope *)getOrCreateScopeScopeId:(NSString *)scopeId qualifier:(id<WPRKSDKKoin_coreQualifier>)qualifier source:(id _Nullable)source __attribute__((swift_name("getOrCreateScope(scopeId:qualifier:source:)")));
- (id _Nullable)getOrNullClazz:(id<WPRKSDKKotlinKClass>)clazz qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(clazz:qualifier:parameters:)")));
- (id _Nullable)getOrNullQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(qualifier:parameters:)")));
- (id _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (id)getPropertyKey:(NSString *)key defaultValue:(id)defaultValue __attribute__((swift_name("getProperty(key:defaultValue:)")));
- (WPRKSDKKoin_coreScope *)getScopeScopeId:(NSString *)scopeId __attribute__((swift_name("getScope(scopeId:)")));
- (WPRKSDKKoin_coreScope * _Nullable)getScopeOrNullScopeId:(NSString *)scopeId __attribute__((swift_name("getScopeOrNull(scopeId:)")));
- (id<WPRKSDKKotlinLazy>)injectQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier mode:(WPRKSDKKotlinLazyThreadSafetyMode *)mode parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("inject(qualifier:mode:parameters:)")));
- (id<WPRKSDKKotlinLazy>)injectOrNullQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier mode:(WPRKSDKKotlinLazyThreadSafetyMode *)mode parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("injectOrNull(qualifier:mode:parameters:)")));
- (void)loadModulesModules:(NSArray<WPRKSDKKoin_coreModule *> *)modules allowOverride:(BOOL)allowOverride __attribute__((swift_name("loadModules(modules:allowOverride:)")));
- (void)setPropertyKey:(NSString *)key value:(id)value __attribute__((swift_name("setProperty(key:value:)")));
- (void)setupLoggerLogger:(WPRKSDKKoin_coreLogger *)logger __attribute__((swift_name("setupLogger(logger:)")));
- (void)unloadModulesModules:(NSArray<WPRKSDKKoin_coreModule *> *)modules __attribute__((swift_name("unloadModules(modules:)")));
@property (readonly) WPRKSDKKoin_coreInstanceRegistry *instanceRegistry __attribute__((swift_name("instanceRegistry")));
@property (readonly) WPRKSDKKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) WPRKSDKKoin_corePropertyRegistry *propertyRegistry __attribute__((swift_name("propertyRegistry")));
@property (readonly) WPRKSDKKoin_coreScopeRegistry *scopeRegistry __attribute__((swift_name("scopeRegistry")));
@end;

__attribute__((swift_name("KotlinThrowable")))
@interface WPRKSDKKotlinThrowable : WPRKSDKBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (WPRKSDKKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) WPRKSDKKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end;

__attribute__((swift_name("KotlinException")))
@interface WPRKSDKKotlinException : WPRKSDKKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinRuntimeException")))
@interface WPRKSDKKotlinRuntimeException : WPRKSDKKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinIllegalStateException")))
@interface WPRKSDKKotlinIllegalStateException : WPRKSDKKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinCancellationException")))
@interface WPRKSDKKotlinCancellationException : WPRKSDKKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(WPRKSDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authAuthResult")))
@interface WPRKSDKFirebase_authAuthResult : WPRKSDKBase
@property (readonly) FIRAuthDataResult *ios __attribute__((swift_name("ios")));
@property (readonly) WPRKSDKFirebase_authFirebaseUser * _Nullable user __attribute__((swift_name("user")));
@end;

__attribute__((swift_name("Firebase_authAuthCredential")))
@interface WPRKSDKFirebase_authAuthCredential : WPRKSDKBase
- (instancetype)initWithIos:(FIRAuthCredential *)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));
@property (readonly) FIRAuthCredential *ios __attribute__((swift_name("ios")));
@property (readonly) NSString *providerId __attribute__((swift_name("providerId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface WPRKSDKKotlinUnit : WPRKSDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) WPRKSDKKotlinUnit *shared __attribute__((swift_name("shared")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authFirebaseAuth")))
@interface WPRKSDKFirebase_authFirebaseAuth : WPRKSDKBase

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)applyActionCodeCode:(NSString *)code completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("applyActionCode(code:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)checkActionCodeCode:(NSString *)code completionHandler:(void (^)(WPRKSDKFirebase_authActionCodeResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("checkActionCode(code:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)confirmPasswordResetCode:(NSString *)code newPassword:(NSString *)newPassword completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("confirmPasswordReset(code:newPassword:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createUserWithEmailAndPasswordEmail:(NSString *)email password:(NSString *)password completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createUserWithEmailAndPassword(email:password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)fetchSignInMethodsForEmailEmail:(NSString *)email completionHandler:(void (^)(NSArray<NSString *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("fetchSignInMethodsForEmail(email:completionHandler:)")));
- (BOOL)isSignInWithEmailLinkLink:(NSString *)link __attribute__((swift_name("isSignInWithEmailLink(link:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendPasswordResetEmailEmail:(NSString *)email actionCodeSettings:(WPRKSDKFirebase_authActionCodeSettings * _Nullable)actionCodeSettings completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendPasswordResetEmail(email:actionCodeSettings:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendSignInLinkToEmailEmail:(NSString *)email actionCodeSettings:(WPRKSDKFirebase_authActionCodeSettings *)actionCodeSettings completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendSignInLinkToEmail(email:actionCodeSettings:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)signInAnonymouslyWithCompletionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("signInAnonymously(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)signInWithCredentialAuthCredential:(WPRKSDKFirebase_authAuthCredential *)authCredential completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("signInWithCredential(authCredential:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)signInWithCustomTokenToken:(NSString *)token completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("signInWithCustomToken(token:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)signInWithEmailAndPasswordEmail:(NSString *)email password:(NSString *)password completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("signInWithEmailAndPassword(email:password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)signInWithEmailLinkEmail:(NSString *)email link:(NSString *)link completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("signInWithEmailLink(email:link:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)signOutWithCompletionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("signOut(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateCurrentUserUser:(WPRKSDKFirebase_authFirebaseUser *)user completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateCurrentUser(user:completionHandler:)")));
- (void)useEmulatorHost:(NSString *)host port:(int32_t)port __attribute__((swift_name("useEmulator(host:port:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)verifyPasswordResetCodeCode:(NSString *)code completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("verifyPasswordResetCode(code:completionHandler:)")));
@property (readonly) id<WPRKSDKKotlinx_coroutines_coreFlow> authStateChanged __attribute__((swift_name("authStateChanged")));
@property (readonly) WPRKSDKFirebase_authFirebaseUser * _Nullable currentUser __attribute__((swift_name("currentUser")));
@property (readonly) id<WPRKSDKKotlinx_coroutines_coreFlow> idTokenChanged __attribute__((swift_name("idTokenChanged")));
@property (readonly) FIRAuth *ios __attribute__((swift_name("ios")));
@property NSString *languageCode __attribute__((swift_name("languageCode")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoinApplication")))
@interface WPRKSDKKoin_coreKoinApplication : WPRKSDKBase
@property (class, readonly, getter=companion) WPRKSDKKoin_coreKoinApplicationCompanion *companion __attribute__((swift_name("companion")));
- (void)allowOverrideOverride:(BOOL)override __attribute__((swift_name("allowOverride(override:)")));
- (void)close __attribute__((swift_name("close()")));
- (WPRKSDKKoin_coreKoinApplication *)loggerLogger:(WPRKSDKKoin_coreLogger *)logger __attribute__((swift_name("logger(logger:)")));
- (WPRKSDKKoin_coreKoinApplication *)modulesModules:(WPRKSDKKotlinArray<WPRKSDKKoin_coreModule *> *)modules __attribute__((swift_name("modules(modules:)")));
- (WPRKSDKKoin_coreKoinApplication *)modulesModules_:(NSArray<WPRKSDKKoin_coreModule *> *)modules __attribute__((swift_name("modules(modules_:)")));
- (WPRKSDKKoin_coreKoinApplication *)modulesModules__:(WPRKSDKKoin_coreModule *)modules __attribute__((swift_name("modules(modules__:)")));
- (WPRKSDKKoin_coreKoinApplication *)printLoggerLevel:(WPRKSDKKoin_coreLevel *)level __attribute__((swift_name("printLogger(level:)")));
- (WPRKSDKKoin_coreKoinApplication *)propertiesValues:(NSDictionary<NSString *, NSString *> *)values __attribute__((swift_name("properties(values:)")));
- (void)unloadModulesModules:(NSArray<WPRKSDKKoin_coreModule *> *)modules __attribute__((swift_name("unloadModules(modules:)")));
- (void)unloadModulesModule:(WPRKSDKKoin_coreModule *)module __attribute__((swift_name("unloadModules(module:)")));
@property (readonly) WPRKSDKKoin_coreKoin *koin __attribute__((swift_name("koin")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreModule")))
@interface WPRKSDKKoin_coreModule : WPRKSDKBase
- (instancetype)initWithCreatedAtStart:(BOOL)createdAtStart __attribute__((swift_name("init(createdAtStart:)"))) __attribute__((objc_designated_initializer));
- (WPRKSDKKotlinPair<WPRKSDKKoin_coreModule *, WPRKSDKKoin_coreInstanceFactory<id> *> *)factoryQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *))definition __attribute__((swift_name("factory(qualifier:definition:)")));
- (NSArray<WPRKSDKKoin_coreModule *> *)plusModules:(NSArray<WPRKSDKKoin_coreModule *> *)modules __attribute__((swift_name("plus(modules:)")));
- (NSArray<WPRKSDKKoin_coreModule *> *)plusModule:(WPRKSDKKoin_coreModule *)module __attribute__((swift_name("plus(module:)")));
- (void)scopeQualifier:(id<WPRKSDKKoin_coreQualifier>)qualifier scopeSet:(void (^)(WPRKSDKKoin_coreScopeDSL *))scopeSet __attribute__((swift_name("scope(qualifier:scopeSet:)")));
- (void)scopeScopeSet:(void (^)(WPRKSDKKoin_coreScopeDSL *))scopeSet __attribute__((swift_name("scope(scopeSet:)")));
- (WPRKSDKKotlinPair<WPRKSDKKoin_coreModule *, WPRKSDKKoin_coreInstanceFactory<id> *> *)singleQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier createdAtStart:(BOOL)createdAtStart definition:(id _Nullable (^)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *))definition __attribute__((swift_name("single(qualifier:createdAtStart:definition:)")));
@property (readonly) BOOL createdAtStart __attribute__((swift_name("createdAtStart")));
@property (readonly) WPRKSDKMutableSet<WPRKSDKKoin_coreSingleInstanceFactory<id> *> *eagerInstances __attribute__((swift_name("eagerInstances")));
@property (readonly) BOOL isLoaded __attribute__((swift_name("isLoaded")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScope")))
@interface WPRKSDKKoin_coreScope : WPRKSDKBase
- (instancetype)initWithScopeQualifier:(id<WPRKSDKKoin_coreQualifier>)scopeQualifier id:(NSString *)id isRoot:(BOOL)isRoot _koin:(WPRKSDKKoin_coreKoin *)_koin __attribute__((swift_name("init(scopeQualifier:id:isRoot:_koin:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (id<WPRKSDKKoin_coreQualifier>)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (WPRKSDKKoin_coreScope *)doCopyScopeQualifier:(id<WPRKSDKKoin_coreQualifier>)scopeQualifier id:(NSString *)id isRoot:(BOOL)isRoot _koin:(WPRKSDKKoin_coreKoin *)_koin __attribute__((swift_name("doCopy(scopeQualifier:id:isRoot:_koin:)")));
- (void)declareInstance:(id _Nullable)instance qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier secondaryTypes:(NSArray<id<WPRKSDKKotlinKClass>> *)secondaryTypes allowOverride:(BOOL)allowOverride __attribute__((swift_name("declare(instance:qualifier:secondaryTypes:allowOverride:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (id _Nullable)getClazz:(id<WPRKSDKKotlinKClass>)clazz qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(clazz:qualifier:parameters:)")));
- (id)getQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(qualifier:parameters:)")));
- (NSArray<id> *)getAll __attribute__((swift_name("getAll()")));
- (NSArray<id> *)getAllClazz:(id<WPRKSDKKotlinKClass>)clazz __attribute__((swift_name("getAll(clazz:)")));
- (WPRKSDKKoin_coreKoin *)getKoin __attribute__((swift_name("getKoin()")));
- (id _Nullable)getOrNullClazz:(id<WPRKSDKKotlinKClass>)clazz qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(clazz:qualifier:parameters:)")));
- (id _Nullable)getOrNullQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(qualifier:parameters:)")));
- (id)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (id)getPropertyKey:(NSString *)key defaultValue:(id)defaultValue __attribute__((swift_name("getProperty(key:defaultValue:)")));
- (id _Nullable)getPropertyOrNullKey:(NSString *)key __attribute__((swift_name("getPropertyOrNull(key:)")));
- (WPRKSDKKoin_coreScope *)getScopeScopeID:(NSString *)scopeID __attribute__((swift_name("getScope(scopeID:)")));
- (id _Nullable)getSource __attribute__((swift_name("getSource()"))) __attribute__((deprecated("No need to use getSource(). You can an use get() directly.")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (id<WPRKSDKKotlinLazy>)injectQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier mode:(WPRKSDKKotlinLazyThreadSafetyMode *)mode parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("inject(qualifier:mode:parameters:)")));
- (id<WPRKSDKKotlinLazy>)injectOrNullQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier mode:(WPRKSDKKotlinLazyThreadSafetyMode *)mode parameters:(WPRKSDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("injectOrNull(qualifier:mode:parameters:)")));
- (BOOL)isNotClosed __attribute__((swift_name("isNotClosed()")));
- (void)linkToScopes:(WPRKSDKKotlinArray<WPRKSDKKoin_coreScope *> *)scopes __attribute__((swift_name("linkTo(scopes:)")));
- (void)refreshScopeInstanceClazz:(id<WPRKSDKKotlinKClass>)clazz qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier instance:(id)instance __attribute__((swift_name("refreshScopeInstance(clazz:qualifier:instance:)")));
- (void)registerCallbackCallback:(id<WPRKSDKKoin_coreScopeCallback>)callback __attribute__((swift_name("registerCallback(callback:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (void)unlinkScopes:(WPRKSDKKotlinArray<WPRKSDKKoin_coreScope *> *)scopes __attribute__((swift_name("unlink(scopes:)")));
@property (readonly) NSMutableArray<WPRKSDKKoin_coreParametersHolder *> *_parameterStack __attribute__((swift_name("_parameterStack")));
@property id _Nullable _source __attribute__((swift_name("_source")));
@property (readonly) BOOL closed __attribute__((swift_name("closed")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) BOOL isRoot __attribute__((swift_name("isRoot")));
@property (readonly) WPRKSDKKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) id<WPRKSDKKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@end;

__attribute__((swift_name("Koin_coreKoinScopeComponent")))
@protocol WPRKSDKKoin_coreKoinScopeComponent <WPRKSDKKoin_coreKoinComponent>
@required
- (void)closeScope __attribute__((swift_name("closeScope()")));
@property (readonly) WPRKSDKKoin_coreScope *scope __attribute__((swift_name("scope")));
@end;

__attribute__((swift_name("Koin_coreQualifier")))
@protocol WPRKSDKKoin_coreQualifier
@required
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol WPRKSDKKotlinKDeclarationContainer
@required
@end;

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol WPRKSDKKotlinKAnnotatedElement
@required
@end;

__attribute__((swift_name("KotlinKClassifier")))
@protocol WPRKSDKKotlinKClassifier
@required
@end;

__attribute__((swift_name("KotlinKClass")))
@protocol WPRKSDKKotlinKClass <WPRKSDKKotlinKDeclarationContainer, WPRKSDKKotlinKAnnotatedElement, WPRKSDKKotlinKClassifier>
@required
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end;

__attribute__((swift_name("Koin_coreParametersHolder")))
@interface WPRKSDKKoin_coreParametersHolder : WPRKSDKBase
- (instancetype)initWith_values:(NSMutableArray<id> *)_values __attribute__((swift_name("init(_values:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) WPRKSDKKoin_coreParametersHolderCompanion *companion __attribute__((swift_name("companion")));
- (WPRKSDKKoin_coreParametersHolder *)addValue:(id)value __attribute__((swift_name("add(value:)")));
- (id _Nullable)component1 __attribute__((swift_name("component1()")));
- (id _Nullable)component2 __attribute__((swift_name("component2()")));
- (id _Nullable)component3 __attribute__((swift_name("component3()")));
- (id _Nullable)component4 __attribute__((swift_name("component4()")));
- (id _Nullable)component5 __attribute__((swift_name("component5()")));
- (id _Nullable)elementAtI:(int32_t)i clazz:(id<WPRKSDKKotlinKClass>)clazz __attribute__((swift_name("elementAt(i:clazz:)")));
- (id)get __attribute__((swift_name("get()")));
- (id _Nullable)getI:(int32_t)i __attribute__((swift_name("get(i:)")));
- (id _Nullable)getOrNull __attribute__((swift_name("getOrNull()")));
- (id _Nullable)getOrNullClazz:(id<WPRKSDKKotlinKClass>)clazz __attribute__((swift_name("getOrNull(clazz:)")));
- (WPRKSDKKoin_coreParametersHolder *)insertIndex:(int32_t)index value:(id)value __attribute__((swift_name("insert(index:value:)")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (BOOL)isNotEmpty __attribute__((swift_name("isNotEmpty()")));
- (void)setI:(int32_t)i t:(id _Nullable)t __attribute__((swift_name("set(i:t:)")));
- (int32_t)size __attribute__((swift_name("size()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<id> *values __attribute__((swift_name("values")));
@end;

__attribute__((swift_name("KotlinLazy")))
@protocol WPRKSDKKotlinLazy
@required
- (BOOL)isInitialized __attribute__((swift_name("isInitialized()")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("KotlinComparable")))
@protocol WPRKSDKKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("KotlinEnum")))
@interface WPRKSDKKotlinEnum<E> : WPRKSDKBase <WPRKSDKKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) WPRKSDKKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinLazyThreadSafetyMode")))
@interface WPRKSDKKotlinLazyThreadSafetyMode : WPRKSDKKotlinEnum<WPRKSDKKotlinLazyThreadSafetyMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) WPRKSDKKotlinLazyThreadSafetyMode *synchronized __attribute__((swift_name("synchronized")));
@property (class, readonly) WPRKSDKKotlinLazyThreadSafetyMode *publication __attribute__((swift_name("publication")));
@property (class, readonly) WPRKSDKKotlinLazyThreadSafetyMode *none __attribute__((swift_name("none")));
+ (WPRKSDKKotlinArray<WPRKSDKKotlinLazyThreadSafetyMode *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("Koin_coreLogger")))
@interface WPRKSDKKoin_coreLogger : WPRKSDKBase
- (instancetype)initWithLevel:(WPRKSDKKoin_coreLevel *)level __attribute__((swift_name("init(level:)"))) __attribute__((objc_designated_initializer));
- (void)debugMsg:(NSString *)msg __attribute__((swift_name("debug(msg:)")));
- (void)errorMsg:(NSString *)msg __attribute__((swift_name("error(msg:)")));
- (void)infoMsg:(NSString *)msg __attribute__((swift_name("info(msg:)")));
- (BOOL)isAtLvl:(WPRKSDKKoin_coreLevel *)lvl __attribute__((swift_name("isAt(lvl:)")));
- (void)logLvl:(WPRKSDKKoin_coreLevel *)lvl msg:(NSString *(^)(void))msg __attribute__((swift_name("log(lvl:msg:)")));
- (void)logLevel:(WPRKSDKKoin_coreLevel *)level msg:(NSString *)msg __attribute__((swift_name("log(level:msg:)")));
@property WPRKSDKKoin_coreLevel *level __attribute__((swift_name("level")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceRegistry")))
@interface WPRKSDKKoin_coreInstanceRegistry : WPRKSDKBase
- (instancetype)initWith_koin:(WPRKSDKKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)saveMappingAllowOverride:(BOOL)allowOverride mapping:(NSString *)mapping factory:(WPRKSDKKoin_coreInstanceFactory<id> *)factory logWarning:(BOOL)logWarning __attribute__((swift_name("saveMapping(allowOverride:mapping:factory:logWarning:)")));
- (int32_t)size __attribute__((swift_name("size()")));
@property (readonly) WPRKSDKKoin_coreKoin *_koin __attribute__((swift_name("_koin")));
@property (readonly) NSDictionary<NSString *, WPRKSDKKoin_coreInstanceFactory<id> *> *instances __attribute__((swift_name("instances")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_corePropertyRegistry")))
@interface WPRKSDKKoin_corePropertyRegistry : WPRKSDKBase
- (instancetype)initWith_koin:(WPRKSDKKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (void)deletePropertyKey:(NSString *)key __attribute__((swift_name("deleteProperty(key:)")));
- (id _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (void)savePropertiesProperties:(NSDictionary<NSString *, id> *)properties __attribute__((swift_name("saveProperties(properties:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeRegistry")))
@interface WPRKSDKKoin_coreScopeRegistry : WPRKSDKBase
- (instancetype)initWith_koin:(WPRKSDKKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) WPRKSDKKoin_coreScopeRegistryCompanion *companion __attribute__((swift_name("companion")));
- (void)loadScopesModules:(NSArray<WPRKSDKKoin_coreModule *> *)modules __attribute__((swift_name("loadScopes(modules:)")));
@property (readonly) WPRKSDKKoin_coreScope *rootScope __attribute__((swift_name("rootScope")));
@property (readonly) NSSet<id<WPRKSDKKoin_coreQualifier>> *scopeDefinitions __attribute__((swift_name("scopeDefinitions")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface WPRKSDKKotlinArray<T> : WPRKSDKBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(WPRKSDKInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<WPRKSDKKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authFirebaseUser")))
@interface WPRKSDKFirebase_authFirebaseUser : WPRKSDKBase

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteWithCompletionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("delete(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getIdTokenForceRefresh:(BOOL)forceRefresh completionHandler:(void (^)(NSString * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getIdToken(forceRefresh:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getIdTokenResultForceRefresh:(BOOL)forceRefresh completionHandler:(void (^)(WPRKSDKFirebase_authAuthTokenResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getIdTokenResult(forceRefresh:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)linkWithCredentialCredential:(WPRKSDKFirebase_authAuthCredential *)credential completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("linkWithCredential(credential:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)reauthenticateCredential:(WPRKSDKFirebase_authAuthCredential *)credential completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reauthenticate(credential:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)reauthenticateAndRetrieveDataCredential:(WPRKSDKFirebase_authAuthCredential *)credential completionHandler:(void (^)(WPRKSDKFirebase_authAuthResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reauthenticateAndRetrieveData(credential:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)reloadWithCompletionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reload(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendEmailVerificationActionCodeSettings:(WPRKSDKFirebase_authActionCodeSettings * _Nullable)actionCodeSettings completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendEmailVerification(actionCodeSettings:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)unlinkProvider:(NSString *)provider completionHandler:(void (^)(WPRKSDKFirebase_authFirebaseUser * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("unlink(provider:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateEmailEmail:(NSString *)email completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateEmail(email:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updatePasswordPassword:(NSString *)password completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updatePassword(password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updatePhoneNumberCredential:(WPRKSDKFirebase_authPhoneAuthCredential *)credential completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updatePhoneNumber(credential:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateProfileDisplayName:(NSString * _Nullable)displayName photoUrl:(NSString * _Nullable)photoUrl completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateProfile(displayName:photoUrl:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)verifyBeforeUpdateEmailNewEmail:(NSString *)newEmail actionCodeSettings:(WPRKSDKFirebase_authActionCodeSettings * _Nullable)actionCodeSettings completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("verifyBeforeUpdateEmail(newEmail:actionCodeSettings:completionHandler:)")));
@property (readonly) NSString * _Nullable displayName __attribute__((swift_name("displayName")));
@property (readonly) NSString * _Nullable email __attribute__((swift_name("email")));
@property (readonly) FIRUser *ios __attribute__((swift_name("ios")));
@property (readonly) BOOL isAnonymous __attribute__((swift_name("isAnonymous")));
@property (readonly) BOOL isEmailVerified __attribute__((swift_name("isEmailVerified")));
@property (readonly) WPRKSDKFirebase_authUserMetaData * _Nullable metaData __attribute__((swift_name("metaData")));
@property (readonly) WPRKSDKFirebase_authMultiFactor *multiFactor __attribute__((swift_name("multiFactor")));
@property (readonly) NSString * _Nullable phoneNumber __attribute__((swift_name("phoneNumber")));
@property (readonly) NSString * _Nullable photoURL __attribute__((swift_name("photoURL")));
@property (readonly) NSArray<WPRKSDKFirebase_authUserInfo *> *providerData __attribute__((swift_name("providerData")));
@property (readonly) NSString *providerId __attribute__((swift_name("providerId")));
@property (readonly) NSString *uid __attribute__((swift_name("uid")));
@end;

__attribute__((swift_name("Firebase_authActionCodeResult")))
@interface WPRKSDKFirebase_authActionCodeResult : WPRKSDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authActionCodeSettings")))
@interface WPRKSDKFirebase_authActionCodeSettings : WPRKSDKBase
- (instancetype)initWithUrl:(NSString *)url androidPackageName:(WPRKSDKFirebase_authAndroidPackageName * _Nullable)androidPackageName dynamicLinkDomain:(NSString * _Nullable)dynamicLinkDomain canHandleCodeInApp:(BOOL)canHandleCodeInApp iOSBundleId:(NSString * _Nullable)iOSBundleId __attribute__((swift_name("init(url:androidPackageName:dynamicLinkDomain:canHandleCodeInApp:iOSBundleId:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (WPRKSDKFirebase_authAndroidPackageName * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (BOOL)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (WPRKSDKFirebase_authActionCodeSettings *)doCopyUrl:(NSString *)url androidPackageName:(WPRKSDKFirebase_authAndroidPackageName * _Nullable)androidPackageName dynamicLinkDomain:(NSString * _Nullable)dynamicLinkDomain canHandleCodeInApp:(BOOL)canHandleCodeInApp iOSBundleId:(NSString * _Nullable)iOSBundleId __attribute__((swift_name("doCopy(url:androidPackageName:dynamicLinkDomain:canHandleCodeInApp:iOSBundleId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) WPRKSDKFirebase_authAndroidPackageName * _Nullable androidPackageName __attribute__((swift_name("androidPackageName")));
@property (readonly) BOOL canHandleCodeInApp __attribute__((swift_name("canHandleCodeInApp")));
@property (readonly) NSString * _Nullable dynamicLinkDomain __attribute__((swift_name("dynamicLinkDomain")));
@property (readonly) NSString * _Nullable iOSBundleId __attribute__((swift_name("iOSBundleId")));
@property (readonly) NSString *url __attribute__((swift_name("url")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol WPRKSDKKotlinx_coroutines_coreFlow
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<WPRKSDKKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoinApplication.Companion")))
@interface WPRKSDKKoin_coreKoinApplicationCompanion : WPRKSDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) WPRKSDKKoin_coreKoinApplicationCompanion *shared __attribute__((swift_name("shared")));
- (WPRKSDKKoin_coreKoinApplication *)doInit __attribute__((swift_name("doInit()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreLevel")))
@interface WPRKSDKKoin_coreLevel : WPRKSDKKotlinEnum<WPRKSDKKoin_coreLevel *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) WPRKSDKKoin_coreLevel *debug __attribute__((swift_name("debug")));
@property (class, readonly) WPRKSDKKoin_coreLevel *info __attribute__((swift_name("info")));
@property (class, readonly) WPRKSDKKoin_coreLevel *error __attribute__((swift_name("error")));
@property (class, readonly) WPRKSDKKoin_coreLevel *none __attribute__((swift_name("none")));
+ (WPRKSDKKotlinArray<WPRKSDKKoin_coreLevel *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("Koin_coreInstanceFactory")))
@interface WPRKSDKKoin_coreInstanceFactory<T> : WPRKSDKBase
- (instancetype)initWithBeanDefinition:(WPRKSDKKoin_coreBeanDefinition<T> *)beanDefinition __attribute__((swift_name("init(beanDefinition:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) WPRKSDKKoin_coreInstanceFactoryCompanion *companion __attribute__((swift_name("companion")));
- (T _Nullable)createContext:(WPRKSDKKoin_coreInstanceContext *)context __attribute__((swift_name("create(context:)")));
- (void)dropScope:(WPRKSDKKoin_coreScope * _Nullable)scope __attribute__((swift_name("drop(scope:)")));
- (void)dropAll __attribute__((swift_name("dropAll()")));
- (T _Nullable)getContext:(WPRKSDKKoin_coreInstanceContext *)context __attribute__((swift_name("get(context:)")));
- (BOOL)isCreatedContext:(WPRKSDKKoin_coreInstanceContext * _Nullable)context __attribute__((swift_name("isCreated(context:)")));
@property (readonly) WPRKSDKKoin_coreBeanDefinition<T> *beanDefinition __attribute__((swift_name("beanDefinition")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinPair")))
@interface WPRKSDKKotlinPair<__covariant A, __covariant B> : WPRKSDKBase
- (instancetype)initWithFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("init(first:second:)"))) __attribute__((objc_designated_initializer));
- (A _Nullable)component1 __attribute__((swift_name("component1()")));
- (B _Nullable)component2 __attribute__((swift_name("component2()")));
- (WPRKSDKKotlinPair<A, B> *)doCopyFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("doCopy(first:second:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) A _Nullable first __attribute__((swift_name("first")));
@property (readonly) B _Nullable second __attribute__((swift_name("second")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeDSL")))
@interface WPRKSDKKoin_coreScopeDSL : WPRKSDKBase
- (instancetype)initWithScopeQualifier:(id<WPRKSDKKoin_coreQualifier>)scopeQualifier module:(WPRKSDKKoin_coreModule *)module __attribute__((swift_name("init(scopeQualifier:module:)"))) __attribute__((objc_designated_initializer));
- (WPRKSDKKotlinPair<WPRKSDKKoin_coreModule *, WPRKSDKKoin_coreInstanceFactory<id> *> *)factoryQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *))definition __attribute__((swift_name("factory(qualifier:definition:)")));
- (WPRKSDKKotlinPair<WPRKSDKKoin_coreModule *, WPRKSDKKoin_coreInstanceFactory<id> *> *)scopedQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *))definition __attribute__((swift_name("scoped(qualifier:definition:)")));
- (WPRKSDKKotlinPair<WPRKSDKKoin_coreModule *, WPRKSDKKoin_coreInstanceFactory<id> *> *)singleQualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *))definition __attribute__((swift_name("single(qualifier:definition:)"))) __attribute__((unavailable("Can't use Single in a scope. Use Scoped instead")));
@property (readonly) WPRKSDKKoin_coreModule *module __attribute__((swift_name("module")));
@property (readonly) id<WPRKSDKKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreSingleInstanceFactory")))
@interface WPRKSDKKoin_coreSingleInstanceFactory<T> : WPRKSDKKoin_coreInstanceFactory<T>
- (instancetype)initWithBeanDefinition:(WPRKSDKKoin_coreBeanDefinition<T> *)beanDefinition __attribute__((swift_name("init(beanDefinition:)"))) __attribute__((objc_designated_initializer));
- (T _Nullable)createContext:(WPRKSDKKoin_coreInstanceContext *)context __attribute__((swift_name("create(context:)")));
- (void)dropScope:(WPRKSDKKoin_coreScope * _Nullable)scope __attribute__((swift_name("drop(scope:)")));
- (void)dropAll __attribute__((swift_name("dropAll()")));
- (T _Nullable)getContext:(WPRKSDKKoin_coreInstanceContext *)context __attribute__((swift_name("get(context:)")));
- (BOOL)isCreatedContext:(WPRKSDKKoin_coreInstanceContext * _Nullable)context __attribute__((swift_name("isCreated(context:)")));
@end;

__attribute__((swift_name("Koin_coreScopeCallback")))
@protocol WPRKSDKKoin_coreScopeCallback
@required
- (void)onScopeCloseScope:(WPRKSDKKoin_coreScope *)scope __attribute__((swift_name("onScopeClose(scope:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreParametersHolder.Companion")))
@interface WPRKSDKKoin_coreParametersHolderCompanion : WPRKSDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) WPRKSDKKoin_coreParametersHolderCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) int32_t MAX_PARAMS __attribute__((swift_name("MAX_PARAMS")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface WPRKSDKKotlinEnumCompanion : WPRKSDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) WPRKSDKKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeRegistry.Companion")))
@interface WPRKSDKKoin_coreScopeRegistryCompanion : WPRKSDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) WPRKSDKKoin_coreScopeRegistryCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol WPRKSDKKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authAuthTokenResult")))
@interface WPRKSDKFirebase_authAuthTokenResult : WPRKSDKBase
- (instancetype)initWithIos:(FIRAuthTokenResult *)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSDictionary<NSString *, id> *claims __attribute__((swift_name("claims")));
@property (readonly) FIRAuthTokenResult *ios __attribute__((swift_name("ios")));
@property (readonly) NSString * _Nullable signInProvider __attribute__((swift_name("signInProvider")));
@property (readonly) NSString * _Nullable token __attribute__((swift_name("token")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authPhoneAuthCredential")))
@interface WPRKSDKFirebase_authPhoneAuthCredential : WPRKSDKFirebase_authAuthCredential
- (instancetype)initWithIos:(FIRPhoneAuthCredential *)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));
@property (readonly) FIRPhoneAuthCredential *ios __attribute__((swift_name("ios")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authUserMetaData")))
@interface WPRKSDKFirebase_authUserMetaData : WPRKSDKBase
- (instancetype)initWithIos:(FIRUserMetadata *)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));
@property (readonly) WPRKSDKDouble * _Nullable creationTime __attribute__((swift_name("creationTime")));
@property (readonly) FIRUserMetadata *ios __attribute__((swift_name("ios")));
@property (readonly) WPRKSDKDouble * _Nullable lastSignInTime __attribute__((swift_name("lastSignInTime")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authMultiFactor")))
@interface WPRKSDKFirebase_authMultiFactor : WPRKSDKBase
- (instancetype)initWithIos:(FIRMultiFactor *)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)enrollMultiFactorAssertion:(WPRKSDKFirebase_authMultiFactorAssertion *)multiFactorAssertion displayName:(NSString * _Nullable)displayName completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("enroll(multiFactorAssertion:displayName:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getSessionWithCompletionHandler:(void (^)(WPRKSDKFirebase_authMultiFactorSession * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getSession(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)unenrollMultiFactorInfo:(WPRKSDKFirebase_authMultiFactorInfo *)multiFactorInfo completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("unenroll(multiFactorInfo:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)unenrollFactorUid:(NSString *)factorUid completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("unenroll(factorUid:completionHandler:)")));
@property (readonly) NSArray<WPRKSDKFirebase_authMultiFactorInfo *> *enrolledFactors __attribute__((swift_name("enrolledFactors")));
@property (readonly) FIRMultiFactor *ios __attribute__((swift_name("ios")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authUserInfo")))
@interface WPRKSDKFirebase_authUserInfo : WPRKSDKBase
- (instancetype)initWithIos:(id<FIRUserInfo>)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString * _Nullable displayName __attribute__((swift_name("displayName")));
@property (readonly) NSString * _Nullable email __attribute__((swift_name("email")));
@property (readonly) id<FIRUserInfo> ios __attribute__((swift_name("ios")));
@property (readonly) NSString * _Nullable phoneNumber __attribute__((swift_name("phoneNumber")));
@property (readonly) NSString * _Nullable photoURL __attribute__((swift_name("photoURL")));
@property (readonly) NSString *providerId __attribute__((swift_name("providerId")));
@property (readonly) NSString *uid __attribute__((swift_name("uid")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authAndroidPackageName")))
@interface WPRKSDKFirebase_authAndroidPackageName : WPRKSDKBase
- (instancetype)initWithPackageName:(NSString *)packageName installIfNotAvailable:(BOOL)installIfNotAvailable minimumVersion:(NSString * _Nullable)minimumVersion __attribute__((swift_name("init(packageName:installIfNotAvailable:minimumVersion:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (BOOL)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (WPRKSDKFirebase_authAndroidPackageName *)doCopyPackageName:(NSString *)packageName installIfNotAvailable:(BOOL)installIfNotAvailable minimumVersion:(NSString * _Nullable)minimumVersion __attribute__((swift_name("doCopy(packageName:installIfNotAvailable:minimumVersion:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL installIfNotAvailable __attribute__((swift_name("installIfNotAvailable")));
@property (readonly) NSString * _Nullable minimumVersion __attribute__((swift_name("minimumVersion")));
@property (readonly) NSString *packageName __attribute__((swift_name("packageName")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol WPRKSDKKotlinx_coroutines_coreFlowCollector
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(WPRKSDKKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreBeanDefinition")))
@interface WPRKSDKKoin_coreBeanDefinition<T> : WPRKSDKBase
- (instancetype)initWithScopeQualifier:(id<WPRKSDKKoin_coreQualifier>)scopeQualifier primaryType:(id<WPRKSDKKotlinKClass>)primaryType qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier definition:(T _Nullable (^)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *))definition kind:(WPRKSDKKoin_coreKind *)kind secondaryTypes:(NSArray<id<WPRKSDKKotlinKClass>> *)secondaryTypes __attribute__((swift_name("init(scopeQualifier:primaryType:qualifier:definition:kind:secondaryTypes:)"))) __attribute__((objc_designated_initializer));
- (id<WPRKSDKKoin_coreQualifier>)component1 __attribute__((swift_name("component1()")));
- (id<WPRKSDKKotlinKClass>)component2 __attribute__((swift_name("component2()")));
- (id<WPRKSDKKoin_coreQualifier> _Nullable)component3 __attribute__((swift_name("component3()")));
- (T _Nullable (^)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *))component4 __attribute__((swift_name("component4()")));
- (WPRKSDKKoin_coreKind *)component5 __attribute__((swift_name("component5()")));
- (NSArray<id<WPRKSDKKotlinKClass>> *)component6 __attribute__((swift_name("component6()")));
- (WPRKSDKKoin_coreBeanDefinition<T> *)doCopyScopeQualifier:(id<WPRKSDKKoin_coreQualifier>)scopeQualifier primaryType:(id<WPRKSDKKotlinKClass>)primaryType qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier definition:(T _Nullable (^)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *))definition kind:(WPRKSDKKoin_coreKind *)kind secondaryTypes:(NSArray<id<WPRKSDKKotlinKClass>> *)secondaryTypes __attribute__((swift_name("doCopy(scopeQualifier:primaryType:qualifier:definition:kind:secondaryTypes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (BOOL)hasTypeClazz:(id<WPRKSDKKotlinKClass>)clazz __attribute__((swift_name("hasType(clazz:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isClazz:(id<WPRKSDKKotlinKClass>)clazz qualifier:(id<WPRKSDKKoin_coreQualifier> _Nullable)qualifier scopeDefinition:(id<WPRKSDKKoin_coreQualifier>)scopeDefinition __attribute__((swift_name("is(clazz:qualifier:scopeDefinition:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property WPRKSDKKoin_coreCallbacks<T> *callbacks __attribute__((swift_name("callbacks")));
@property (readonly) T _Nullable (^definition)(WPRKSDKKoin_coreScope *, WPRKSDKKoin_coreParametersHolder *) __attribute__((swift_name("definition")));
@property (readonly) WPRKSDKKoin_coreKind *kind __attribute__((swift_name("kind")));
@property (readonly) id<WPRKSDKKotlinKClass> primaryType __attribute__((swift_name("primaryType")));
@property (readonly) id<WPRKSDKKoin_coreQualifier> _Nullable qualifier __attribute__((swift_name("qualifier")));
@property (readonly) id<WPRKSDKKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@property NSArray<id<WPRKSDKKotlinKClass>> *secondaryTypes __attribute__((swift_name("secondaryTypes")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceFactoryCompanion")))
@interface WPRKSDKKoin_coreInstanceFactoryCompanion : WPRKSDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) WPRKSDKKoin_coreInstanceFactoryCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *ERROR_SEPARATOR __attribute__((swift_name("ERROR_SEPARATOR")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceContext")))
@interface WPRKSDKKoin_coreInstanceContext : WPRKSDKBase
- (instancetype)initWithKoin:(WPRKSDKKoin_coreKoin *)koin scope:(WPRKSDKKoin_coreScope *)scope parameters:(WPRKSDKKoin_coreParametersHolder * _Nullable)parameters __attribute__((swift_name("init(koin:scope:parameters:)"))) __attribute__((objc_designated_initializer));
@property (readonly) WPRKSDKKoin_coreKoin *koin __attribute__((swift_name("koin")));
@property (readonly) WPRKSDKKoin_coreParametersHolder * _Nullable parameters __attribute__((swift_name("parameters")));
@property (readonly) WPRKSDKKoin_coreScope *scope __attribute__((swift_name("scope")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authMultiFactorAssertion")))
@interface WPRKSDKFirebase_authMultiFactorAssertion : WPRKSDKBase
- (instancetype)initWithIos:(FIRMultiFactorAssertion *)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *factorId __attribute__((swift_name("factorId")));
@property (readonly) FIRMultiFactorAssertion *ios __attribute__((swift_name("ios")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authMultiFactorSession")))
@interface WPRKSDKFirebase_authMultiFactorSession : WPRKSDKBase
- (instancetype)initWithIos:(FIRMultiFactorSession *)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));
@property (readonly) FIRMultiFactorSession *ios __attribute__((swift_name("ios")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Firebase_authMultiFactorInfo")))
@interface WPRKSDKFirebase_authMultiFactorInfo : WPRKSDKBase
- (instancetype)initWithIos:(FIRMultiFactorInfo *)ios __attribute__((swift_name("init(ios:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString * _Nullable displayName __attribute__((swift_name("displayName")));
@property (readonly) double enrollmentTime __attribute__((swift_name("enrollmentTime")));
@property (readonly) NSString *factorId __attribute__((swift_name("factorId")));
@property (readonly) FIRMultiFactorInfo *ios __attribute__((swift_name("ios")));
@property (readonly) NSString *uid __attribute__((swift_name("uid")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKind")))
@interface WPRKSDKKoin_coreKind : WPRKSDKKotlinEnum<WPRKSDKKoin_coreKind *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) WPRKSDKKoin_coreKind *singleton __attribute__((swift_name("singleton")));
@property (class, readonly) WPRKSDKKoin_coreKind *factory __attribute__((swift_name("factory")));
@property (class, readonly) WPRKSDKKoin_coreKind *scoped __attribute__((swift_name("scoped")));
+ (WPRKSDKKotlinArray<WPRKSDKKoin_coreKind *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreCallbacks")))
@interface WPRKSDKKoin_coreCallbacks<T> : WPRKSDKBase
- (instancetype)initWithOnClose:(void (^ _Nullable)(T _Nullable))onClose __attribute__((swift_name("init(onClose:)"))) __attribute__((objc_designated_initializer));
- (void (^ _Nullable)(T _Nullable))component1 __attribute__((swift_name("component1()")));
- (WPRKSDKKoin_coreCallbacks<T> *)doCopyOnClose:(void (^ _Nullable)(T _Nullable))onClose __attribute__((swift_name("doCopy(onClose:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^ _Nullable onClose)(T _Nullable) __attribute__((swift_name("onClose")));
@end;

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
