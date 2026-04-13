import os

base_path = os.getcwd()

filenames = []

for root, dirs, files in os.walk(base_path):
    for file in files:
        name, _ = os.path.splitext(file)
        filenames.append(f'"{name}"')

output = ',\n'.join(filenames)

with open('file_list.txt', 'w') as f:
    f.write(output + '\n')

print(f"{len(filenames)} file names written to file_list.txt")
