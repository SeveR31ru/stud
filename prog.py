def ERROR():
	raise Exception

def seq():
	ch = _read()
	if ch == '(':
		roundContinue()
		seq()
	elif ch == '[':
		squareContinue()
		seq()
	elif ch != END_STRING:
		ERROR()
		
def round():
	ch = _read()
	if ch == '(':
		print("s")
		
def roundContinue():
	ch = _read()
	if ch == '(':
		roundContinue()
		roundContinue()
	elif ch != ')':
		ERROR()
	
def squareContinue():
	ch = _read()
	if ch == '[':
		squareContinue()
		squareContinue()
	elif ch == '(':
		roundContinue()
		squareContinue()
	elif ch != ']':
		ERROR()
		
		
def _read():
	global index
	if(index >= len(string)):
		return END_STRING
	
	letter = string[index]
	index += 1
	return letter
	
END_STRING = '#'

if True:
	string = input("Введите последовательность: ")
	index = 0
	try:
		seq()
		print("Последовательность правильная")
	except Exception:
		print("Последовательность неправильная!")