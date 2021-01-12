package spring.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/students")
class Controller {

    @RequestMapping("/is-academic", produces = ["application/json"])
    fun isAcademic(email: String): String {
        val isAcademic = swot.isAcademic(email)
        return "{\"input\": \"$email\",\"is-academic\": $isAcademic}"
    }

    @RequestMapping("/school-names", produces = ["application/json"])
    fun getSchoolNames(email: String): List<String> {
        return swot.findSchoolNames(email)
    }

}
