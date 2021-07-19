package com.xtrem.peads_cardiac.helpers

class PhoneContact {
    fun isValidTNMNumber(phone: String): Boolean {
        var phone = phone
        phone = sanitizePhoneNumber(phone)
        return phone.length >= 9 && phone.substring(phone.length - 9).substring(
            0,
            2
        ) == "88" || phone.length >= 9 && phone.substring(phone.length - 9).substring(
            0,
            2
        ) == "31"
    }

    fun isValidNumber(phone: String): Boolean {
        var phone = phone
        phone = sanitizePhoneNumber(phone)
        return phone.length >= 9 && phone.substring(phone.length - 9).substring(
            0,
            2
        ) == "88" || phone.length >= 9 && phone.substring(phone.length - 9).substring(
            0,
            2
        ) == "99" || phone.length >= 9 && phone.substring(phone.length - 9).substring(
            0,
            2
        ) == "31"
    }

    private fun sanitizePhoneNumber(phone: String): String {
        return phone.replace("\\D+".toRegex(), "")
    }
}