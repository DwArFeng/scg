# ChangeLog

## Release_1.3.0_20260112_build_A

### 功能构建

- 优化文件格式。
  - 优化 `*.properties` 文件的格式。
  - 优化 `opt-*.xml` 文件的格式。
  - 优化 `application-context-*.xml` 文件的格式。
  - 优化 `pom.xml` 文件的格式。

- 优化开发环境支持。
  - 在 .gitignore 中添加 VSCode 相关文件的忽略规则。
  - 在 .gitignore 中添加 Cursor IDE 相关文件的忽略规则。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_1.2.4_20231215_build_A

### 功能构建

- 优化 `telqos/connection.properties` 配置文件的默认值。

- 添加 sdk Bean。
  - com.dwarfeng.scg.sdk.bean.entity.JSFixedFastJsonCommonVariable。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_1.2.3_20231122_build_A

### 功能构建

- 依赖升级。
  - 升级 `zookeeper` 依赖版本为 `3.7.2` 以规避漏洞。

### Bug 修复

- 修改预置的 groovy 脚本中的 bug。
  - scg-impl/src/main/resources/groovy/ExampleGeneratorProcessor.groovy。

### 功能移除

- (无)

---

## Release_1.2.2_20230914_build_A

### 功能构建

- 新增服务。
  - com.dwarfeng.scg.stack.service.PostStyleGenerateService。

### Bug 修复

- 补充 node 模块 `spring/application-context-dubbo.xml` 中缺失的配置。

- 修正 Hibernate 实体字段列注解中错误的列名。
  - com.dwarfeng.scg.impl.bean.entity.HibernateNodeVariable.stringValue。
  - com.dwarfeng.scg.impl.bean.entity.HibernateCommonVariable.stringValue。

### 功能移除

- (无)

---

## Release_1.2.1_20230909_build_A

### 功能构建

- 新增 `subgrade` 项目的集成组件。
  - com.dwarfeng.scg.api.integration.subgrade.ScgStringGenerator。
  - com.dwarfeng.scg.api.integration.subgrade.ScgStringIdKeyGenerator。

- 新增 api 模块。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.4.5.a` 并解决兼容性问题，以应用其新功能。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_1.2.0_20230907_build_A

### 功能构建

- 优化 `GenerateQosService` 的方法。

- 重构流水码的生成逻辑。
  - **这将导致新版本的生成器插件与旧版本不兼容。**

- 增加实体字段。
  - com.dwarfeng.scg.stack.bean.entity.ScgSetting.granularity。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_1.1.0_20230902_build_A

### 功能构建

- 启停脚本优化。
  - 优化 Windows 系统的启动脚本。
  - 优化 Linux 系统的启停脚本。

- dubbo 微服务增加分组配置。

- 实现核心机制。
  - 推送机制。
  - 重置机制。

- 重构流水码生成逻辑。

- 优化 `spring-telqos` 结构。
  - 优化指令注解。
  - 使用 `package-scan` 扫描 `telqos` 包内所有指令。

- 定义实体的操作服务。
  - com.dwarfeng.scg.stack.service.NodeVariableOperateService。
  - com.dwarfeng.scg.stack.service.CommonVariableOperateService。

- 定义实体及其维护服务，并通过单元测试。
  - com.dwarfeng.scg.stack.bean.entity.NodeVariable。
  - com.dwarfeng.scg.stack.bean.entity.CommonVariable。

- 使用 `subgrade` 工具库替代本地缓存实现。

- 将 Bean 的注入形式由注解注入改为构造器注入。

- 使用 `MapStruct` 重构 `BeanTransformer`。

- 增加依赖。
  - 增加依赖 `protobuf` 以规避漏洞，版本为 `3.19.6`。
  - 增加依赖 `guava` 以规避漏洞，版本为 `2.0.1-jre`。
  - 增加依赖 `gson` 以规避漏洞，版本为 `2.8.9`。
  - 增加依赖 `snakeyaml` 以规避漏洞，版本为 `2.0`。
  - 增加依赖 `jackson` 以规避漏洞，版本为 `2.14.0`。

- 依赖升级。
  - 升级 `spring` 依赖版本为 `5.3.27` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.0.31` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.8.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.5` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.5.7` 以规避漏洞。
  - 升级 `curator` 依赖版本为 `4.3.0` 以规避漏洞。
  - 升级 `hibernate` 依赖版本为 `5.4.24.Final` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.2.5.Final` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.2.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.11.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.3.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.10.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.11.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.7.a` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `4.0.6` 以规避漏洞。

- 插件升级
  - 升级 `maven-compiler-plugin` 插件版本为 `3.1.0`。

- 优化文件格式。
  - 优化 `pom.xml` 文件的格式。
  - 优化 `opt-*.xml` 文件的格式。

### Bug 修复

- (无)

### 功能移除

- 删除实体中不需要的字段。
  - com.dwarfeng.scg.stack.bean.entity.ScgSetting.distributed。

- 删除不需要的实体及其维护服务。
  - com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo。

- 删除不需要的依赖。
  - 删除 `dozer` 依赖。
  - 删除 `el` 依赖。
  - 删除 `zkclient` 依赖。
  - 删除 `aopalliance` 依赖。

---

## Release_1.0.2_20220928_build_A

### 功能构建

- 优化项目结构。
  - 增加 `confext` 文件夹，用于加载外部的配置文件。

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 依赖升级。
  - 升级 `dutil` 依赖版本为 `beta-0.3.1.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.10.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.9.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.4.a` 以规避漏洞。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_1.0.1_20220608_build_A

### 功能构建

- 增加 DTO 实体字段。
  - com.dwarfeng.scg.stack.bean.dto.GenerateInfo.distributed。
    - 用于告知生成器当前流水码生成设置是否使用分布式生成。

### Bug 修复

- 修正 `ExampleGeneratorProcessor` 中不正确的逻辑。

### 功能移除

- (无)

---

## Release_1.0.0_20220607_build_A

### 功能构建

- 项目建立，清除测试通过。

- 实体建立，单元测试通过。
  - com.dwarfeng.scg.stack.bean.entity.GeneratorSupport。
  - com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo。
  - com.dwarfeng.scg.stack.bean.entity.ScgSetting。

- 生成器机制实现，实现默认生成器。
  - com.dwarfeng.scg.impl.handler.generator.AbstractGeneratorRegistry。
  - com.dwarfeng.scg.impl.handler.generator.GroovyGeneratorRegistry。

- 生成服务完成。
  - com.dwarfeng.scg.stack.service.GenerateService。

- 程序开发完成，运行测试通过，打包测试通过。

- 创建运维指令。
  - com.dwarfeng.scg.impl.service.telqos.GenerateCommand。
  - com.dwarfeng.scg.impl.service.telqos.GenerateLocalCacheCommand。
  - com.dwarfeng.scg.impl.service.telqos.LockLocalCacheCommand。

### Bug 修复

- (无)

### 功能移除

- (无)
