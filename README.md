SinRESTClient
=============

A Simple REST Client Library Implemented by Java

It't very easy to use it, in most time you can use it as this follow:

<ul>
<li>
Create a WebResource witch is Implemented by REST Server Protocol.

<pre>
<code>WebResource resource = new WebResource("http://sinpy.sinaapp.com/rest/book/");</code>
</pre>
</li>

<li>
Then use this resource to create a ResourceAccess.

<pre>
<code>ResourceAccess access = resource.get();	// A GET access, maybe it will get all resource
ResourceAccess access = resource.get("1");	// A GET access, it will get the resource witch id==1 (maybe other identity equal "1")
ResourceAccess access = resource.post(); // A POST access, use it to add a resource
ResourceAccess access = resource.put(); // A PUT access, use it to modify resource
ResourceAccess access = resource.delete("1"); // A DELETE access, use it to delete resource, the mean of "1" is like before.</code>
</pre>
</li>

<li>
After create ResourceAccess, you can set the access's attribute, such as encode, type, accept type and so on.

<pre>
<code>
access.accept(MediaType.APPLICATION_JSON); // Set accept type is JSON string
</code>
</pre>
</li>

<li>
Everything is ready, now use read() to execute the ResourceAccess, when read() you can submit some data to the REST Server.

<pre>
<code>access.read();	// Simple read
access.read(String);	// Submit a String and read the response
access.read(Map<String, String>);	// Submit a Data Form and read the response</code>
</pre>
</li>

<li>
Maybe, use one line statement to get response is more quickly for.
<pre>
<code>// delete the resource witch id is 120
resource.delete("120").accept(MediaType.APPLICATION_JSON).read();</code>
</pre>
</li>

</ul>

