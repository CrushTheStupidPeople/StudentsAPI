package swot

import java.io.File
import java.io.FileInputStream

fun isAcademic(email: String): Boolean {
    val parts = domainParts(email)
    return !isStoplisted(parts) && (isUnderTLD(parts) || findSchoolNames(parts).isNotEmpty())
}

fun findSchoolNames(emailOrDomain: String): List<String> {
    return findSchoolNames(domainParts(emailOrDomain))
}

fun isUnderTLD(parts: List<String>): Boolean {
    return checkSet(Resources.tlds, parts)
}

fun isStoplisted(parts: List<String>): Boolean {
    return checkSet(Resources.stoplist, parts)
}

private object Resources {
    val tlds = readList("/tlds.txt") ?: error("Cannot find /tlds.txt")
    val stoplist = readList("/stoplist.txt") ?: error("Cannot find /stoplist.txt")

    fun readList(resource: String) : Set<String>? {
        // CRISP - Since we added spring, we changed the package system. The stuff with classpath didn't resolve any more.
        val file = File("lib/domains" + resource)
        if (!file.exists())
            return null
        return file?.inputStream()?.reader()?.buffered()?.lineSequence()?.toHashSet()
    }
}

private fun findSchoolNames(parts: List<String>): List<String> {
    val resourcePath = StringBuilder("")
    for (part in parts) {
        resourcePath.append('/').append(part)
        val school = Resources.readList("${resourcePath}.txt")
        if (school != null) {
            return school.toList()
        }
    }

    return arrayListOf()
}

private fun domainParts(emailOrDomain: String): List<String> {
    return emailOrDomain.trim().toLowerCase().substringAfter('@').substringAfter("://").substringBefore(':').split('.').reversed()
}

internal fun checkSet(set: Set<String>, parts: List<String>): Boolean {
    val subj = StringBuilder()
    for (part in parts) {
        subj.insert(0, part)
        if (set.contains(subj.toString())) return true
        subj.insert(0 ,'.')
    }
    return false
}
