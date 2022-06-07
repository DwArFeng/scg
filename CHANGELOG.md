# ChangeLog

### Release_1.0.0_20220604_build_A

#### 功能构建

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

#### Bug修复

- (无)

#### 功能移除

- (无)
