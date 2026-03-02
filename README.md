# Yachiyo项目API文档

## 1. 项目概述

Yachiyo是一个完整的AI聊天系统，包含以下组件：

- **后端服务** (`YachiyoService`): 基于Spring Boot的Java服务，提供AI聊天和语音合成功能
- **WPF前端** (`EasyYachiyoClient`): 基于C#的桌面应用，提供丰富的用户界面
- **Python前端** (`YachiyoClient`): 基于Tkinter的简单桌面应用
- **Python前端(旧)** (`EasyYachiyoClient-old`): 早期版本的Python前端
- **模型文件** (`ollama`): 包含模型训练配置

## 2. API接口文档

### 2.1 基础URL

默认基础URL: `http://localhost:8080`

### 2.2 接口列表

| 接口路径 | 方法 | 功能描述 | 请求体 | 响应 |
|---------|------|----------|--------|------|
| `/api/v1/ai/chat` | POST | AI聊天 | 文本消息 | AI回复文本 |
| `/api/v1/ai/speak` | POST | 文本转语音 | 文本消息 | 音频二进制数据 |

### 2.3 详细接口说明

#### 2.3.1 聊天接口

**接口**: `POST /api/v1/ai/chat`

**功能**: 发送消息到AI并获取回复

**请求体**:
```json
"你好，今天天气怎么样？"
```

**响应**:
```
"今天天气很好，阳光明媚，适合外出活动。"
```

**实现说明**:
- 使用Spring AI的ChatClient与AI模型交互
- 直接返回AI的回复内容

#### 2.3.2 语音合成接口

**接口**: `POST /api/v1/ai/speak`

**功能**: 将文本转换为语音

**请求体**:
```json
"你好，我是Yachiyo。"
```

**响应**:
- 音频二进制数据（byte[]）

**实现说明**:
1. 首先将文本翻译成日语
2. 调用外部TTS服务（`http://0.0.0.0:9882`）生成语音
3. 返回音频二进制数据

## 3. 前端调用示例

### 3.1 Python前端调用

```python
import requests

# 发送聊天请求
url = "http://localhost:8080/api/v1/ai/chat"
message = "你好，今天天气怎么样？"
response = requests.post(url, data=message)
print(response.text)

# 发送语音合成请求
url = "http://localhost:8080/api/v1/ai/speak"
message = "你好，我是Yachiyo。"
response = requests.post(url, data=message)
# 保存音频文件
with open("output.mp3", "wb") as f:
    f.write(response.content)
```

### 3.2 WPF前端调用

```csharp
// 发送聊天请求
using HttpClient client = new HttpClient();
var requestBody = new { prompt = "你好，今天天气怎么样？" };
string jsonBody = JsonSerializer.Serialize(requestBody);
var content = new StringContent(jsonBody, Encoding.UTF8, "application/json");
string apiUrl = "http://localhost:8080/api/v1/ai/chat";
HttpResponseMessage response = await client.PostAsync(apiUrl, content);
string responseContent = await response.Content.ReadAsStringAsync();

// 发送语音合成请求
apiUrl = "http://localhost:8080/api/v1/ai/speak";
content = new StringContent("你好，我是Yachiyo。", Encoding.UTF8, "text/plain");
response = await client.PostAsync(apiUrl, content);
byte[] audioData = await response.Content.ReadAsByteArrayAsync();
```

## 4. 服务依赖关系

### 4.1 后端服务依赖

- **Spring Boot**: 基础框架
- **Spring AI**: 提供AI聊天功能
- **RestTemplate**: 用于HTTP请求
- **百度翻译API**: 用于文本翻译
- **外部TTS服务**: 运行在 `http://0.0.0.0:9882`

### 4.2 前端依赖

#### WPF前端
- **.NET 8.0**: 运行环境
- **CefSharp**: 用于Web视图
- **SevenZipExtractor**: 用于解压功能

#### Python前端
- **Python 3.x**: 运行环境
- **tkinter**: 用于GUI
- **requests**: 用于HTTP请求

## 5. 部署说明

### 5.1 后端服务部署

1. **环境准备**:
   - JDK 17+
   - Maven 3.6+

2. **构建步骤**:
   ```bash
   cd YachiyoService
   mvn clean package
   ```

3. **运行服务**:
   ```bash
   java -jar Controller/target/Controller-0.0.1-SNAPSHOT.jar
   ```

4. **配置文件**:
   - `Config/src/main/resources/application.yml`: 应用配置
   - `Config/src/main/resources/application-secret.yml`: 密钥配置

### 5.2 前端部署

#### WPF前端
1. 使用Visual Studio打开 `EasyYachiyoClient.sln`
2. 构建解决方案
3. 运行生成的可执行文件

#### Python前端
1. 安装依赖:
   ```bash
   pip install requests
   ```
2. 运行应用:
   ```bash
   python yachiyo.py
   ```

## 6. 配置选项

### 6.1 后端配置

- **服务端口**: 默认8080
- **AI模型配置**: 在 `AIConfig.java` 中设置
- **翻译API配置**: 需要在 `TransUtil` 中配置百度翻译API密钥

### 6.2 前端配置

#### WPF前端
- **API地址配置**: 在 `AddressConfigurationManager.cs` 中设置
- **其他配置**: 在 `SettingsManager.cs` 中管理

#### Python前端
- **API地址配置**: 在 `config.json` 文件中设置

## 7. 项目结构

```
TsukimiYachiyo/
├── EasyYachiyoClient/         # WPF前端
│   ├── Model/                 # 数据模型
│   ├── Utils/                 # 工具类
│   ├── ViewModel/             # 视图模型
│   └── resource/              # 资源文件
├── EasyYachiyoClient-old/     # 旧Python前端
├── ollama/                    # 模型文件
│   ├── v0.0.1/                # 版本1
│   └── v0.0.2/                # 版本2
├── YachiyoClient/             # Python前端
└── YachiyoService/            # 后端服务
    ├── Common/                # 通用模块
    ├── Config/                # 配置模块
    ├── Controller/            # 控制器
    ├── Service/               # 服务层
    └── dto/                   # 数据传输对象
```

## 8. 故障排除

### 8.1 常见问题

1. **API连接失败**:
   - 检查后端服务是否运行
   - 检查API地址配置是否正确

2. **语音合成失败**:
   - 检查外部TTS服务是否运行在 `http://0.0.0.0:9882`
   - 检查翻译API配置是否正确

3. **前端启动失败**:
   - WPF前端: 检查.NET 8.0运行时是否安装
   - Python前端: 检查Python环境和依赖是否正确

### 8.2 日志

- **后端日志**: 控制台输出
- **WPF前端日志**: `bin/Debug/net8.0-windows/logs/` 目录

## 9. 版本历史

| 版本 | 日期 | 变更内容 |
|------|------|----------|
| v0.0.1 | - | 初始版本 |
| v0.0.2 | - | 模型更新 |

## 10. 联系方式

如有问题，请联系项目维护者。