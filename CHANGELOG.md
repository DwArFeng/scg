# ChangeLog

### Release_1.0.1_20220608_build_A

#### 功能构建

- 增加 DTO 实体字段。
  - com.dwarfeng.scg.stack.bean.dto.GenerateInfo.distributed。
    - 用于告知生成器当前流水码生成设置是否使用分布式生成。

#### Bug修复

- 修正 `ExampleGeneratorProcessor` 中不正确的逻辑。

#### 功能移除

- (无)

---

### Release_1.0.0_20220607_build_A

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
