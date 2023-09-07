package groovy

import com.dwarfeng.scg.impl.handler.generator.GroovyGeneratorRegistry
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey
import org.springframework.stereotype.Component

import static com.dwarfeng.scg.stack.handler.Generator.*

/**
 * 示例生成器。
 *
 * <p>
 * 该生成器获取当前日期，并将序列号自增。<br>
 * 前缀 + 当前日期 + 设备ID + 添加前导 0 的序列号组成流水码。<br>
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

    private static final String VARIABLE_ID_LAST_GENERATED_DATE = "last_generated_date"
    private static final String VARIABLE_ID_LAST_INDEX = "last_index"
    private static final long MILLISECONDS_OF_DAY = 3600 * 24 * 1000L

    private static int analyseCurrentIndex(Context context, StringIdKey scgSettingKey, Date lastDate, Date currentDate)
            throws Exception {
        Integer referenceIndex = context.inspectNodeVariable(
                scgSettingKey, VARIABLE_ID_LAST_INDEX, VariableType.INTEGER
        ) as Integer
        if (Objects.nonNull(lastDate)) {
            TimeZone defaultTimeZone = TimeZone.getDefault()
            long lastTimestamp = lastDate.getTime()
            long currentTimestamp = currentDate.getTime()
            int lastLocalDay = (int) (
                    (lastTimestamp - defaultTimeZone.getOffset(lastTimestamp)) / MILLISECONDS_OF_DAY
            )
            int currentLocalDay = (int) (
                    (currentTimestamp - defaultTimeZone.getOffset(currentTimestamp)) / MILLISECONDS_OF_DAY
            )
            return currentLocalDay > lastLocalDay ? 0 : referenceIndex
        } else {
            return 0
        }
    }

    @Override
    String generate(Context context, ContextInfo contextInfo) throws Exception {
        int deviceId = context.getDeviceId()
        StringIdKey scgSettingKey = contextInfo.getScgSettingKey()
        Date currentDate = new Date()
        Date lastDate = context.inspectNodeVariable(
                scgSettingKey, VARIABLE_ID_LAST_GENERATED_DATE, VariableType.DATE
        ) as Date
        int currentIndex = analyseCurrentIndex(context, scgSettingKey, lastDate, currentDate)
        String format = "%1\$s%2\$tY%2\$tm%2\$td%3\$0" + NUMBER_OF_DIGITS_DEVICE_ID + "d%4\$0" +
                NUMBER_OF_DIGITS_INDEX + "d"
        String result = String.format(format, PREFIX, currentDate, deviceId, currentIndex++)
        context.upsertNodeVariable(scgSettingKey, VARIABLE_ID_LAST_GENERATED_DATE, VariableType.DATE, currentDate)
        context.upsertNodeVariable(scgSettingKey, VARIABLE_ID_LAST_INDEX, VariableType.INTEGER, currentIndex)
        return result
    }

    @Override
    List<String> generate(Context context, ContextInfo contextInfo, int size) throws Exception {
        int deviceId = context.getDeviceId()
        StringIdKey scgSettingKey = contextInfo.getScgSettingKey()
        Date currentDate = new Date()
        Date lastDate = context.inspectNodeVariable(
                scgSettingKey, VARIABLE_ID_LAST_GENERATED_DATE, VariableType.DATE
        ) as Date
        int currentIndex = analyseCurrentIndex(context, scgSettingKey, lastDate, currentDate)
        List<String> result = new ArrayList<>(size)
        for (int i = 0; i < size; i++) {
            String format = "%1\$s%2\$tY%2\$tm%2\$td%3\$0" + NUMBER_OF_DIGITS_DEVICE_ID + "d%4\$0" +
                    NUMBER_OF_DIGITS_INDEX + "d"
            String serialCode = String.format(format, PREFIX, currentDate, deviceId, currentIndex++)
            result.add(serialCode)
        }
        context.upsertNodeVariable(scgSettingKey, VARIABLE_ID_LAST_GENERATED_DATE, VariableType.DATE, currentDate)
        context.upsertNodeVariable(scgSettingKey, VARIABLE_ID_LAST_INDEX, VariableType.INTEGER, currentIndex)
        return result
    }
}
