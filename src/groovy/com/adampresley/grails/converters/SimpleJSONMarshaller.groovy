package com.adampresley.grails.converters

import grails.converters.JSON
import groovy.lang.GroovyObject
import org.codehaus.groovy.grails.web.converters.marshaller.json.MapMarshaller
import org.codehaus.groovy.grails.web.converters.marshaller.json.GroovyBeanMarshaller

class SimpleJSONMarshaller extends GroovyBeanMarshaller {
	void marshalObject(Object o, JSON json) {
		def flag_useMapMashaller = false
		def whatToSerialize = o
		def methods = ((GroovyObject)o).metaClass.methods*.name.unique()

		methods.each {
			if (it == "toJSON") {
				flag_useMapMashaller = true
				whatToSerialize = o.toJSON()
			}
		}

		if (!flag_useMapMashaller)
			this.marshalObject(whatToSerialize, json)
		else
			new MapMarshaller().marshalObject(whatToSerialize, json)
	}
}