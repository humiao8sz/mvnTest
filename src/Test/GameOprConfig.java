package Test;

import java.util.List;
import java.util.Set;

public class GameOprConfig {

	//产品代码
	private String gamecode;
	//产品ID
	private String productId;
	//运营商代码
	private String pfcode;
	//运营商ID
	private String oprId;
	//登录密钥
	private String loginKey;
	//充值密钥
	private String payKey;
	//运营商登录验证接口
	private String loginAPI;
	//运营商注册接口
	private String regAPI;
	//运营商查询订单接口
	private String queryOrderAPI;
	//获取联运商ID的接口
	private String getOperatorAPI;
	//应用名称
	private String appName;
	//应用ID
	private String appId;
	//应用Key
	private String appKey;
	//协议版本号
	private String appVersion;
	//运营商接口调用IP限制
	private List<String> ips;
	//true:混服
	private boolean multiOprs;
	//渠道号（大唐高鸿适用）
	private String channelId;

	//客户端请求是否加密标识
	private boolean clientEncryptFlag;
	//客户端请求加密密钥
	private String clientEncryptKey;
	//是否开启消息推送
	private boolean apnsEnable;
	//消息推送服务器
	private String apnsKey;
	//是否以运营商ID为ios_apns_device_token的n_area_id；
	private boolean apnsByOprId;

	//游戏服务器对应的域名
	private String domain;
	//请求游戏服务器登录票据接口
	private String ticketApi;
	//游戏服务器登录接口
	private String loginApi;
	//游戏服务器充值接口
	private String imprestApi;
	//游戏其他业务相关的接口
	private String gameApi;
	private String createOrderApi;

	//游戏服务器列表
	private List<String> servers;
	//皇帝崛起极聚平台需要支持授权登录的服务器
	private List<String> kingAuthServers;
	//部分平台需要商户服务端提交创建订单和支付通知地址[eg.gionee]
	private String oprCreateOrderAPI;
	private String oprOrderNotifyAPI;
	//联通wostore
	private String appdeveloper;
	private String cpid;
	//包名集合
	private Set<String> packageNameSet;
	//主运营商
	private String mainOprId;
	//VerifyHandler处理类名
	private String verifyHandlerClassName;
	//CallbackHandler处理类名
	private String callbackHandlerClassName;
	//ProxyHandler
	private String proxyHandlerClassName;
	//LoginHandler
	private String loginHandlerClassName;
	//TrialHandler
	private String trialHandlerClassName;
	//RegisterHandler
	private String registerHandlerClassName;
	//SupportHandler
	private String supportHandlerClassName;
	//什么时候开始生效
	private long notLoginTime = 0;
	//什么时候开始生效
	private long notCreateRoleTime = 0;
	//什么时候开始生效
	private long notPayTime = 0;

	public boolean isNotPay() {
		if(notPayTime > 0){
			if(System.currentTimeMillis()<notPayTime){
				return false;
			}
			notPayTime = -1;
		}
		return notPayTime == -1;
	}

	
	public long getNotPayTime() {
		return notPayTime;
	}

	public void setNotPayTime(long notPayTime) {
		this.notPayTime = notPayTime;
	}
	
	public boolean isNotLogin() {
		if(notLoginTime > 0){
			if(System.currentTimeMillis()<notLoginTime){
				return false;
			}
			notLoginTime = -1;
		}
		return notLoginTime == -1;
	}
	
	public long getNotLoginTime() {
		return notLoginTime;
	}

	public void setNotLoginTime(long notLoginTime) {
		this.notLoginTime = notLoginTime;
	}

	public boolean isNotCreateRole() {
		if(notCreateRoleTime > 0){
			if(System.currentTimeMillis()<notCreateRoleTime){
				return false;
			}
			notCreateRoleTime = -1;
		}
		return notCreateRoleTime == -1;
	}
	
	public long getNotCreateRoleTime() {
		return notCreateRoleTime;
	}

	public void setNotCreateRoleTime(long notCreateRoleTime) {
		this.notCreateRoleTime = notCreateRoleTime;
	}

	public boolean isAllowedIP(String ip) {
		return ips == null || ips.isEmpty() || ips.contains(ip);
	}

	public String getOprId() {
		return oprId;
	}

	public void setOprId(String oprId) {
		this.oprId = oprId;
	}

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	public String getLoginAPI() {
		return loginAPI;
	}

	public void setLoginAPI(String loginAPI) {
		this.loginAPI = loginAPI;
	}

	public String getRegAPI() {
		return regAPI;
	}

	public void setRegAPI(String regAPI) {
		this.regAPI = regAPI;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getGamecode() {
		return gamecode;
	}

	public void setGamecode(String gamecode) {
		this.gamecode = gamecode;
	}

	public String getPfcode() {
		return pfcode;
	}

	public void setPfcode(String pfcode) {
		this.pfcode = pfcode;
	}

	public boolean isClientEncryptFlag() {
		return clientEncryptFlag;
	}

	public void setClientEncryptFlag(boolean clientEncryptFlag) {
		this.clientEncryptFlag = clientEncryptFlag;
	}

	public String getClientEncryptKey() {
		return clientEncryptKey;
	}

	public void setClientEncryptKey(String clientEncryptKey) {
		this.clientEncryptKey = clientEncryptKey;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTicketApi() {
		return ticketApi;
	}

	public void setTicketApi(String ticketApi) {
		this.ticketApi = ticketApi;
	}

	public String getLoginApi() {
		return loginApi;
	}

	public void setLoginApi(String loginApi) {
		this.loginApi = loginApi;
	}

	public String getImprestApi() {
		return imprestApi;
	}

	public void setImprestApi(String imprestApi) {
		this.imprestApi = imprestApi;
	}

	public List<String> getServers() {
		return servers;
	}

	public void setServers(List<String> servers) {
		this.servers = servers;
	}

	public String getGameApi() {
		return gameApi;
	}

	public void setGameApi(String gameApi) {
		this.gameApi = gameApi;
	}

	public List<String> getIps() {
		return ips;
	}
	
	public Set<String> getPackageNameSet() {
		return packageNameSet;
	}

	public void setPackageNameSet(Set<String> packageNameSet) {
		this.packageNameSet = packageNameSet;
	}

	public String getMainOprId() {
		return mainOprId;
	}

	public void setMainOprId(String mainOprId) {
		this.mainOprId = mainOprId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public boolean isMultiOprs() {
		return multiOprs;
	}

	public void setMultiOprs(boolean multiOprs) {
		this.multiOprs = multiOprs;
	}

	public String getGetOperatorAPI() {
		return getOperatorAPI;
	}

	public void setGetOperatorAPI(String getOperatorAPI) {
		this.getOperatorAPI = getOperatorAPI;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public boolean isApnsEnable() {
		//return apnsEnable;
		//TODO 强制关闭
		return apnsEnable && false;
	}

	public void setApnsEnable(boolean apnsEnable) {
		this.apnsEnable = apnsEnable;
	}

	public String getQueryOrderAPI() {
		return queryOrderAPI;
	}

	public void setQueryOrderAPI(String queryOrderAPI) {
		this.queryOrderAPI = queryOrderAPI;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getApnsKey() {
		return apnsKey;
	}

	public void setApnsKey(String apnsKey) {
		this.apnsKey = apnsKey;
	}

	public List<String> getKingAuthServers() {
		return kingAuthServers;
	}

	public void setKingAuthServers(List<String> kingAuthServers) {
		this.kingAuthServers = kingAuthServers;
	}

	public String getCreateOrderApi() {
		return createOrderApi;
	}

	public void setCreateOrderApi(String createOrderApi) {
		this.createOrderApi = createOrderApi;
	}

	public boolean isApnsByOprId() {
		return apnsByOprId;
	}

	public void setApnsByOprId(boolean apnsByOprId) {
		this.apnsByOprId = apnsByOprId;
	}

	public String getOprCreateOrderAPI() {
		return oprCreateOrderAPI;
	}

	public void setOprCreateOrderAPI(String oprCreateOrderAPI) {
		this.oprCreateOrderAPI = oprCreateOrderAPI;
	}

	public String getOprOrderNotifyAPI() {
		return oprOrderNotifyAPI;
	}

	public void setOprOrderNotifyAPI(String oprOrderNotifyAPI) {
		this.oprOrderNotifyAPI = oprOrderNotifyAPI;
	}

	public String getAppdeveloper() {
		return appdeveloper;
	}

	public void setAppdeveloper(String appdeveloper) {
		this.appdeveloper = appdeveloper;
	}

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getVerifyHandlerClassName() {
		return verifyHandlerClassName;
	}

	public void setVerifyHandlerClassName(String verifyHandlerClassName) {
		this.verifyHandlerClassName = verifyHandlerClassName;
	}

	public String getCallbackHandlerClassName() {
		return callbackHandlerClassName;
	}

	public void setCallbackHandlerClassName(String callbackHandlerClassName) {
		this.callbackHandlerClassName = callbackHandlerClassName;
	}

	public String getProxyHandlerClassName() {
		return proxyHandlerClassName;
	}

	public void setProxyHandlerClassName(String proxyHandlerClassName) {
		this.proxyHandlerClassName = proxyHandlerClassName;
	}

	public String getLoginHandlerClassName() {
		return loginHandlerClassName;
	}

	public void setLoginHandlerClassName(String loginHandlerClassName) {
		this.loginHandlerClassName = loginHandlerClassName;
	}

	public String getTrialHandlerClassName() {
		return trialHandlerClassName;
	}

	public void setTrialHandlerClassName(String trialHandlerClassName) {
		this.trialHandlerClassName = trialHandlerClassName;
	}

	public String getRegisterHandlerClassName() {
		return registerHandlerClassName;
	}

	public void setRegisterHandlerClassName(String registerHandlerClassName) {
		this.registerHandlerClassName = registerHandlerClassName;
	}

	public String getSupportHandlerClassName() {
		return supportHandlerClassName;
	}

	public void setSupportHandlerClassName(String supportHandlerClassName) {
		this.supportHandlerClassName = supportHandlerClassName;
	}
}
