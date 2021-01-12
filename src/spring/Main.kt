package spring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import java.io.File
import java.nio.file.FileSystems

@SpringBootApplication
@ComponentScan(basePackages = ["spring.rest"])
class Main

fun main(args: Array<String>) {
    // initialize swot
    swot.main(args)

    SpringApplication.run(Main::class.java, *args)
    
}
