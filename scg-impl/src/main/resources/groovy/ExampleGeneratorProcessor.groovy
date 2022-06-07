package groovy


import com.dwarfeng.scg.impl.handler.generator.GroovyGeneratorRegistry
import com.dwarfeng.scg.stack.bean.dto.GenerateInfo
import com.dwarfeng.scg.stack.bean.dto.GenerateResult
import com.dwarfeng.scg.stack.exception.GeneratorException
import org.springframework.stereotype.Component

/**
 * 示例生成器。
 *
 * <p>
 * 该生成器获取当前日期，并将序列号自增。<br>
 * 前缀 + 当前日期 + 设备ID + 添加前导0的序列号组成流水码。<br>
 * 流水码一天一清。
 */
@SuppressWarnings("GrPackage")
@Component
class ExampleGeneratorProcessor implements GroovyGeneratorRegistry.Processor {

    /**
     * 串码的前缀。
     */
    public static final String PREFIX = "example"

    /**
     * 序号的位数, 如 2 则最后的设备号输出是 01, 02...
     */
    public static final int NUMBER_OF_DIGITS_DEVICE_ID = 2

    /**
     * 序号的位数, 如 4 则最后的序号输出是 0001, 0002...
     */
    public static final int NUMBER_OF_DIGITS_INDEX = 4

    private static final long MILLISECONDS_OF_DAY = 3600 * 24 * 1000L

    @Override
    GenerateResult generate(GenerateInfo generateInfo) throws GeneratorException {
        Date lastGeneratedDate = generateInfo.getLastGeneratedDate()
        Integer index = generateInfo.getLastIndex()
        int deviceId = generateInfo.getDeviceId()

        Integer neoIndex = Objects.isNull(index) ? 0 : index + 1
        Date currentDate = new Date()
        String serialCode = String.format(
                "%1\$s%2\$tY%2\$tm%2\$td%3\$0" + NUMBER_OF_DIGITS_DEVICE_ID + "d%4\$0" + NUMBER_OF_DIGITS_INDEX + "d",
                PREFIX, currentDate, deviceId, neoIndex
        )
        if (Objects.nonNull(lastGeneratedDate)) {
            TimeZone defaultTimeZone = TimeZone.getDefault()
            long lastGeneratedTimestamp = lastGeneratedDate.getTime()
            long currentTimestamp = currentDate.getTime()
            int lastGeneratedLocalDay = (int) ((lastGeneratedTimestamp - defaultTimeZone.getOffset(lastGeneratedTimestamp)) / MILLISECONDS_OF_DAY)
            int currentLocalDay = (int) ((currentTimestamp - defaultTimeZone.getOffset(currentTimestamp)) / MILLISECONDS_OF_DAY)
            if (currentLocalDay > lastGeneratedLocalDay) {
                neoIndex = 0
            }
        }
        return new GenerateResult(serialCode, neoIndex)
    }
}
