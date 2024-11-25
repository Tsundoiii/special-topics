import json

from challenges.yellow import Yellow

solves: dict = {"blue": False, "yellow": False}

with open("solves.json", "w") as solves_file:
    json.dump(solves, solves_file)

print(Yellow.solved(Yellow()))
