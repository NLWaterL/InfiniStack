import os
import re
from collections import defaultdict

# ─── Pattern Registry ─────────────────────────────────────────────────────────
#
# Each entry: (key, label, regex, re_flags)
#
ALL_PATTERNS = [
    (
        'break_block',
        'Break Block  — nextInt(21) + 10',
        r'nextInt\s*\(\s*21\s*\)\s*\+\s*10',
        re.MULTILINE,
    ),
    (
        'inventory_limit',
        'Inventory Limit — func_70297_j_() { return 64; }',
        r'public\s+int\s+func_70297_j_\s*\(\s*\)\s*\{\s*return\s+64\s*;\s*\}',
        re.MULTILINE | re.DOTALL,
    ),
    (
        'slot_limit',
        'Slot Stack Limit — func_75219_a() { return 64; }',
        r'public\s+int\s+func_75219_a\s*\(\s*\)\s*\{\s*return\s+64\s*;\s*\}',
        re.MULTILINE | re.DOTALL,
    ),
]

# ─── Java File Utilities ──────────────────────────────────────────────────────

def extract_class_name(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        m = re.search(r'public\s+class\s+(\w+)', content)
        if not m:
            m = re.search(r'class\s+(\w+)', content)
        return m.group(1) if m else None
    except Exception as e:
        print(f"  [!] Error extracting class name from {file_path}: {e}")
        return None

def extract_package(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        m = re.search(r'package\s+([^;]+);', content)
        return m.group(1).strip() if m else None
    except Exception as e:
        print(f"  [!] Error extracting package from {file_path}: {e}")
        return None

# ─── File Scanning ────────────────────────────────────────────────────────────

def scan_java_files(root_dir, active_patterns):
    """
    Walk root_dir and classify every .java file by which active patterns it matches.
    Returns a dict keyed by the immediate child folder of root_dir, where each
    value is a list of (file_path, set_of_match_keys).
    active_patterns: list of (key, label, regex, flags) tuples to check.
    """
    results = defaultdict(list)

    for dirpath, _, filenames in os.walk(root_dir):
        for filename in filenames:
            if not filename.endswith('.java'):
                continue

            file_path = os.path.join(dirpath, filename)

            try:
                with open(file_path, 'r', encoding='utf-8') as f:
                    content = f.read()
            except Exception as e:
                print(f"  [!] Cannot read {file_path}: {e}")
                continue

            matches = set()
            for key, _label, pattern, flags in active_patterns:
                if re.search(pattern, content, flags):
                    matches.add(key)

            if matches:
                rel = os.path.relpath(file_path, root_dir)
                top_folder = rel.split(os.sep)[0]
                results[top_folder].append((file_path, matches))

    return results

# ─── Mixin Template Generators ────────────────────────────────────────────────

def make_break_block_mixin(class_name, package_name, mixin_subdir):
    import_path = f"{package_name}.{class_name}" if package_name else class_name
    return f"""\
package com.phasico.infinistack.mixins.{mixin_subdir};

import {import_path};
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin({class_name}.class)
@Pseudo
public abstract class Mixin{class_name} {{

    @Redirect(
            method = "func_149749_a",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            ), remap = false
    )
    private int noStackSplit(Random random, int bound) {{
        if (bound == 21) {{
            return Integer.MAX_VALUE - 10;
        }}
        return random.nextInt(bound);
    }}

}}
"""

def make_inventory_limit_mixin(class_name, package_name, mixin_subdir):
    import_path = f"{package_name}.{class_name}" if package_name else class_name
    return f"""\
package com.phasico.infinistack.mixins.{mixin_subdir};

import {import_path};
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin({class_name}.class)
@Pseudo
public abstract class Mixin{class_name} {{

    @Overwrite(remap = false)
    public int func_70297_j_() {{
        return Configurables.maxStackSize;
    }}

}}
"""

def make_slot_limit_mixin(class_name, package_name, mixin_subdir):
    import_path = f"{package_name}.{class_name}" if package_name else class_name
    return f"""\
package com.phasico.infinistack.mixins.{mixin_subdir};

import {import_path};
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin({class_name}.class)
@Pseudo
public abstract class Mixin{class_name} {{

    @Overwrite(remap = false)
    public int func_75219_a() {{
        return Configurables.maxStackSize;
    }}

}}
"""

# Map each pattern key to its generator and a short file suffix
MIXIN_MAKERS = {
    'break_block':      (make_break_block_mixin,      'BB'),
    'inventory_limit':  (make_inventory_limit_mixin,  'IL'),
    'slot_limit':       (make_slot_limit_mixin,        'SL'),
}

# ─── Mixin File Writer ────────────────────────────────────────────────────────

def write_mixin_file(root_dir, mixin_subdir, filename, content):
    out_dir = os.path.join(root_dir, 'mixins', mixin_subdir)
    os.makedirs(out_dir, exist_ok=True)
    out_path = os.path.join(out_dir, filename)
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write(content)
    print(f"  [+] Written: {out_path}")

# ─── Helpers ──────────────────────────────────────────────────────────────────

def validate_package_name(name):
    return bool(re.match(r'^[a-z][a-z0-9_]*$', name))

def ask_yn(prompt):
    while True:
        ans = input(prompt + " [y/n]: ").strip().lower()
        if ans in ('y', 'yes'):
            return True
        if ans in ('n', 'no'):
            return False
        print("  Please enter y or n.")

# ─── Main ─────────────────────────────────────────────────────────────────────

def main():
    # ── Step 1: Choose which patterns to search ───────────────────────────────
    print("=" * 60)
    print("  Mixin Generator — pattern selection")
    print("=" * 60)

    active_patterns = []
    for key, label, pattern, flags in ALL_PATTERNS:
        if ask_yn(f"Search for {label}?"):
            active_patterns.append((key, label, pattern, flags))

    if not active_patterns:
        print("\n[!] No patterns selected. Exiting.")
        return

    print()

    # ── Step 2: Root directory ────────────────────────────────────────────────
    root_dir = input("Enter root directory path: ").strip()
    if not os.path.isdir(root_dir):
        print(f"[!] Directory not found: {root_dir}")
        return

    # ── Step 3: Scan ─────────────────────────────────────────────────────────
    active_keys = {k for k, *_ in active_patterns}
    key_to_label = {k: lbl for k, lbl, *_ in ALL_PATTERNS}

    print(f"\nScanning {root_dir} ...")
    hits = scan_java_files(root_dir, active_patterns)

    if not hits:
        print("No matching Java files found.")
        return

    # ── Step 4: Per-folder package name prompt ────────────────────────────────
    folder_to_package = {}

    print(f"\nFound matches in {len(hits)} mod source folder(s):\n")
    for folder_name, file_list in sorted(hits.items()):
        found_types = set()
        for _, match_types in file_list:
            found_types |= match_types

        type_labels = [key_to_label[k] for k in sorted(found_types) if k in key_to_label]

        print(f"  Folder : {folder_name}")
        print(f"  Files  : {len(file_list)}")
        print(f"  Types  : {', '.join(type_labels)}")

        while True:
            pkg = input(f"  → Mixin package name for '{folder_name}': ").strip()
            if not pkg:
                print("    [!] Package name cannot be empty.")
                continue
            if not validate_package_name(pkg):
                print("    [!] Warning: name doesn't follow Java package conventions, but continuing.")
            folder_to_package[folder_name] = pkg
            break

        print()

    # ── Step 5: Generate mixin files ──────────────────────────────────────────
    print("Generating mixin files...\n")
    total = 0

    for folder_name, file_list in sorted(hits.items()):
        mixin_subdir = folder_to_package[folder_name]

        for file_path, match_types in file_list:
            class_name = extract_class_name(file_path)
            if not class_name:
                print(f"  [!] Could not extract class name from {file_path}, skipping.")
                continue

            package_name = extract_package(file_path)

            print(f"  File  : {os.path.relpath(file_path, root_dir)}")
            print(f"  Class : {class_name}  |  Package: {package_name or '(none)'}")

            # If only one pattern matched this class, use plain MixinClassName.java.
            # If multiple matched, append a short suffix per pattern to avoid collisions.
            needs_suffix = len(match_types) > 1

            for key in sorted(match_types):
                if key not in MIXIN_MAKERS:
                    continue
                maker_fn, suffix = MIXIN_MAKERS[key]
                content = maker_fn(class_name, package_name, mixin_subdir)
                fname = f"Mixin{class_name}_{suffix}.java" if needs_suffix else f"Mixin{class_name}.java"
                write_mixin_file(root_dir, mixin_subdir, fname, content)
                total += 1

            print()

    print(f"Done. {total} mixin file(s) generated under {os.path.join(root_dir, 'mixins')}/")

if __name__ == "__main__":
    main()