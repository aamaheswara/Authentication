package com.android.authentication.model

class listGame {

    private var name: String = ""
    private var title: String = ""
    private var description: String = ""
    private var id: String = ""

    constructor(name: String, title: String, description: String, id: String){
        this.name = name
        this.title = title
        this.description = description
        this.id = id
    }

    fun getName(): String{
        return name
    }
    fun getTitle(): String{
        return title
    }

    fun getDescription(): String{
        return description
    }

    fun getId(): String{
        return id
    }

    fun setName(name: String){
        this.name = name
    }
    fun setTitle(title: String){
        this.title = title
    }
    fun setDescription(desc: String){
        this.description = desc
    }
}