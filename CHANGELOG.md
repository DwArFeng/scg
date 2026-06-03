# ChangeLog

## Release_2.0.0_20260603_build_A

### 功能构建

- 部分代理类实现中的字段类型提升为对应的接口，与具体实现解耦。
  - com.dwarfeng.scg.impl.cache.CommonVariableCacheImpl。
  - com.dwarfeng.scg.impl.cache.GeneratorSupportCacheImpl。
  - com.dwarfeng.scg.impl.cache.NodeVariableCacheImpl。
  - com.dwarfeng.scg.impl.cache.ScgSettingCacheImpl。
  - com.dwarfeng.scg.impl.dao.CommonVariableDaoImpl。
  - com.dwarfeng.scg.impl.dao.GeneratorSupportDaoImpl。
  - com.dwarfeng.scg.impl.dao.NodeVariableDaoImpl。
  - com.dwarfeng.scg.impl.dao.ScgSettingDaoImpl。
  - com.dwarfeng.scg.impl.service.CommonVariableMaintainServiceImpl。
  - com.dwarfeng.scg.impl.service.GeneratorSupportMaintainServiceImpl。
  - com.dwarfeng.scg.impl.service.NodeVariableMaintainServiceImpl。
  - com.dwarfeng.scg.impl.service.ScgSettingMaintainServiceImpl。

- 优化项目的异常处理机制。
  - `scg-sdk` 子模块新增 `ServiceExceptionHelper` 工具类，统一维护项目自身的异常映射关系。
  - `scg-impl` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。
  - `scg-node` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。

- 依赖升级。
  - 升级 `spring-telqos` 依赖版本为 `2.0.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring-terminator` 依赖版本为 `2.0.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-datamark` 依赖版本为 `2.2.0.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `subgrade` 依赖版本为 `1.8.3.a` 以规避漏洞。

- 优化文件格式。
  - 优化 `*.properties` 文件的格式。
  - 优化 `application-context-*.xml` 文件的格式。
  - 优化 `pom.xml` 文件的格式。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## 更早的版本

[View all changelogs](./changelogs)
