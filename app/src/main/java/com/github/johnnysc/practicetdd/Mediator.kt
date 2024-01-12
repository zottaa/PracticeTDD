package com.github.johnnysc.practicetdd


interface Mediator {
    fun addUser(user: User)

    fun send(sender: User, message: String)

    class Base : Mediator {
        private val users = HashSet<User>()
        override fun addUser(user: User) {
            users.add(user)
        }

        override fun send(sender: User, message: String) {
            users.forEach {
                if (it != sender) {
                    it.receive(message)
                }
            }
        }
    }
}