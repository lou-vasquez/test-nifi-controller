import org.apache.nifi.controller.ControllerService
//import com.lou.coolness.lou.MyService

showName='lou test'

log.info('starting lou test')
def flowFile = session.get()
if(!flowFile) return

def ffn = flowFile.getAttribute('filename')
log.info('lou test file {}',ffn)

false && flowFile.getAttributes().each{
	log.info('{}: attribute for {} is {}={}',showName,ffn,it.key,it.value)
}
false && binding.variables.each{ 
	log.info('{}: binding for {} is {}={}',showName,ffn,it.key,it.value)
}
false && session.read(flowFile).with{
  log.info('{}: content for {} is {}',showName,ffn,it.text)  
}

svcId = context.getControllerServiceLookup().getControllerServiceIdentifiers(ControllerService.class).collect{it}.first()
svc = context.getControllerServiceLookup().getControllerService(svcId)
log.info('controller test: {} {}',svc.class, svc)
p1 = svc.getPlusOne(4)
log.info('plus onned to : {}',p1)

session.transfer(flowFile, REL_SUCCESS)

