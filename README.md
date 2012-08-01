# Simple JSON Marshaller

This Grails plugin is a simple little utility to make my life easier for serializing
domain classes to JSON. Here's a sample of a domain classes that uses this feature. 
The first step is to provide a method named **toJSON()** on your domain object and 
have this method return a map representing what you want serialized in JSON.

```
class Person {
	String firstName
	String lastName
	Role role

	def toJSON() {
		[
			id: this.id,
			firstName: this.firstName,
			lastName: this.lastName,
			role: role.toJSON()
		]
	}
}

class Role {
	String roleName
	int securityLevel = 1

	static constraints = {
		securityLevel nullable: false
	}

	def toJSON() {
		[
			id: this.id,
			roleName: this.roleName
		]
	}
}
```

Notice how we are not including **securityLevel** in our JSON response? In this
example we didn't want to. So when we render as JSON it won't show up.

```
def someControllerMethod() {
	render Person.list() as JSON
}
```

### License
Copyright 2012 Adam Presley. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY Adam Presley "AS IS" AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
EVENT SHALL Adam Presley OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

