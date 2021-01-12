package spring.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @RequestMapping("/is-academic", produces = ["application/json"])
    fun test(email: String): String {
        val isAcademic = swot.isAcademic(email)
        return "{\"$email\": $isAcademic}";
    }

}
