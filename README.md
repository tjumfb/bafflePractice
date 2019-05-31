# bafflePractice
## 挡板工程实践
本工具是一个基于aspectj和Spring的方法级挡板工程，实际使用时只需要在方法上加@Baffle注解就可以进行对该方法的拦截。
## 使用方式
####
1.修改配置文件。在Spring配置文件里添加以下代码

` <!--这是必须的-->
  <aop:aspectj-autoproxy/>
  <bean id="baffle" class="baffle.Logging"/>
    <bean id="configProperties" class="org.springframework.beans.factory.config.PropertiesFactoryBean">
        <property name="locations">
            <list>
                <value>classpath:baffle.properties</value>
            </list>
        </property>
    </bean>`
    
  其中value的值根据本地文件自行修改
  
2.根据上一条的配置创建配置文件，并添加键值对

`power=true`

此时挡板默认开启

3.现在就可以在想要进行拦截的方法上加@Baffle注解来完成拦截。Baffle接收boolean类型的value，默认值为true。
只有当配置文件全局开启挡板且单个注解也开启时挡板才会生效。方法的返回值以对象形式保存在本地，文件名为

`当前路径/方法名/参数类型.../参数值`

